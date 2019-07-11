package com.app.thenhpattern.util;

import android.content.Intent;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.app.thenhpattern.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class NavigationExtension {

    static String selectedItemTag;
    static String firstFragmentTag;
    static Boolean isOnFirstFragment;
    static Integer firstFragmentGraphId;
    static SparseArray<String> graphIdToTagMap;

    public static LiveData<NavController> setupWithNavController(BottomNavigationView bottomNavigationView,
                                                                 List<Integer> navGraphIds,
                                                                 FragmentManager fragmentManager,
                                                                 Integer containerId,
                                                                 Intent intent
                                                                 ){
        // Map of tags
        graphIdToTagMap = new SparseArray<>();
        // Result. Mutable live data with the selected controlled
        MutableLiveData<NavController> selectedNavController = new MutableLiveData<>();

        firstFragmentGraphId = 0;

        for(int index=0;index<navGraphIds.size();index++){

            String fragmentTag = getFragmentTag(index);

            Integer navGraphId = navGraphIds.get(index);

            // Find or create the Navigation host fragment
            NavHostFragment navHostFragment = obtainNavHostFragment(
                    fragmentManager,
                    fragmentTag,
                    navGraphId,
                    containerId
            );

            // Obtain its id
            Integer graphId = navHostFragment.getNavController().getGraph().getId();

            if (index == 0) {
                firstFragmentGraphId = graphId;
            }

            // Save to the map
            graphIdToTagMap.put(graphId,fragmentTag);

            // Attach or detach nav host fragment depending on whether it's the selected item.
            if (bottomNavigationView.getSelectedItemId() == graphId) {
                // Update livedata with the selected graph
                selectedNavController.setValue(navHostFragment.getNavController());

                attachNavHostFragment(fragmentManager, navHostFragment, index == 0);
            } else {
                detachNavHostFragment(fragmentManager, navHostFragment);
            }

        }

        // Now connect selecting an item with swapping Fragments
        selectedItemTag = graphIdToTagMap.get(bottomNavigationView.getSelectedItemId());
        firstFragmentTag = graphIdToTagMap.get(firstFragmentGraphId);
        isOnFirstFragment = (selectedItemTag == firstFragmentTag);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if (fragmentManager.isStateSaved()) {
                return false;
            } else {
                String newlySelectedItemTag = graphIdToTagMap.get(menuItem.getItemId());

                if (selectedItemTag != newlySelectedItemTag) {

                    // Pop everything above the first fragment (the "fixed start destination")

                    fragmentManager.popBackStack(firstFragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    NavHostFragment selectedFragment = (NavHostFragment) fragmentManager.findFragmentByTag(newlySelectedItemTag);

                    // Exclude the first fragment tag because it's always in the back stack.
                    if (firstFragmentTag != newlySelectedItemTag) {
                        // Commit a transaction that cleans the back stack and adds the first fragment
                        // to it, creating the fixed started destination.

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction
                                .attach(selectedFragment)
                                .setPrimaryNavigationFragment(selectedFragment);

                        for(int i = 0; i < graphIdToTagMap.size(); i++) {
                            int key = graphIdToTagMap.keyAt(i);

                            String obj = graphIdToTagMap.get(key);

                            if (obj != newlySelectedItemTag) {
                                fragmentTransaction.detach(fragmentManager.findFragmentByTag(firstFragmentTag));
                            }

                        }

                        fragmentTransaction.addToBackStack(firstFragmentTag)
                                .setCustomAnimations(
                                        R.anim.nav_default_enter_anim,
                                        R.anim.nav_default_exit_anim,
                                        R.anim.nav_default_pop_enter_anim,
                                        R.anim.nav_default_pop_exit_anim)
                                .setReorderingAllowed(true)
                                .commit();

                    }

                    selectedItemTag = newlySelectedItemTag;
                    isOnFirstFragment = (selectedItemTag == firstFragmentTag);
                    selectedNavController.setValue(selectedFragment.getNavController());
                    return true;
                } else {
                    return false;
                }
            }
        });


        // Optional: on item reselected, pop back stack to the destination of the graph
        setupItemReselected(bottomNavigationView,graphIdToTagMap, fragmentManager);

        setupDeepLinks(bottomNavigationView,navGraphIds, fragmentManager, containerId, intent);
        // Finally, ensure that we update our BottomNavigationView when the back stack changes

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (!isOnFirstFragment && !isOnBackStack(fragmentManager,firstFragmentTag)) {
                    bottomNavigationView.setSelectedItemId(firstFragmentGraphId);
                }

                // Reset the graph if the currentDestination is not valid (happens when the back
                // stack is popped after using the back button).

                if(selectedNavController.getValue() != null){
                    if(selectedNavController.getValue().getCurrentDestination() == null){
                        selectedNavController.getValue().navigate(selectedNavController.getValue().getGraph().getId());
                    }
                }

            }
        });


        return selectedNavController;
    }

    FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {

        }
    };

    private static Boolean isOnBackStack(FragmentManager fragmentManager,String backStackName) {
        Integer backStackCount = fragmentManager.getBackStackEntryCount();

        for(int index =0;index <backStackCount;index++){
            if (fragmentManager.getBackStackEntryAt(index).getName() == backStackName) {
                return true;
            }
        }
        return false;
    }

    private static void setupItemReselected(BottomNavigationView bottomNavigationView,
            SparseArray<String> graphIdToTagMap,
            FragmentManager fragmentManager
    ) {
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                String newlySelectedItemTag = graphIdToTagMap.get(menuItem.getItemId());
                NavHostFragment selectedFragment = (NavHostFragment) fragmentManager.findFragmentByTag(newlySelectedItemTag);
                NavController navController = selectedFragment.getNavController();
                // Pop the back stack to the start destination of the current navController graph
                navController.popBackStack(
                        navController.getGraph().getStartDestination(), false
                );
            }
        });
    }

    private static  void setupDeepLinks(BottomNavigationView view,
            List<Integer> navGraphIds,
            FragmentManager fragmentManager,
            Integer containerId,
            Intent intent
    ) {

        for(int index=0;index<navGraphIds.size();index++){
            String fragmentTag = getFragmentTag(index);
            Integer navGraphId = navGraphIds.get(index);

            NavHostFragment navHostFragment = obtainNavHostFragment(
                    fragmentManager,
                    fragmentTag,
                    navGraphId,
                    containerId
            );
            if (navHostFragment.getNavController().handleDeepLink(intent)
                    && view.getSelectedItemId() != navHostFragment.getNavController().getGraph().getId()) {
                view.setSelectedItemId(navHostFragment.getNavController().getGraph().getId());
            }
        }

    }


    private  static void detachNavHostFragment(
            FragmentManager fragmentManager,
            NavHostFragment navHostFragment
    ) {
        fragmentManager.beginTransaction()
                .detach(navHostFragment)
                .commitNow();
    }

    private static void attachNavHostFragment(
            FragmentManager fragmentManager,
            NavHostFragment navHostFragment,
            Boolean isPrimaryNavFragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.attach(navHostFragment).commitNow();

        if (isPrimaryNavFragment) {
            fragmentTransaction.setPrimaryNavigationFragment(navHostFragment);
        }

    }


    private static NavHostFragment obtainNavHostFragment(FragmentManager fragmentManager, String fragmentTag,Integer navGraphId, Integer containerId){
        // If the Nav Host fragment exists, return it
        NavHostFragment existingFragment = (NavHostFragment) fragmentManager.findFragmentByTag(fragmentTag);

        if(existingFragment != null){
            return existingFragment;
        }else{
            // Otherwise, create it and return it.
            NavHostFragment navHostFragment = NavHostFragment.create(navGraphId);
            fragmentManager.beginTransaction()
                    .add(containerId, navHostFragment, fragmentTag)
                    .commitNow();
            return navHostFragment;
        }
    }

    private static String getFragmentTag(Integer index){
        return "bottomNavigation#"+index;
    }


}
