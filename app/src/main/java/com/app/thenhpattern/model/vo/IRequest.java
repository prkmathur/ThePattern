package com.app.thenhpattern.model.vo;

import com.google.gson.Gson;
import java.util.Map;

public interface IRequest {

          default String getRequestJson() {
               Gson gson = new Gson();
               return gson.toJson(this);

          }

          default
               Map<String, String> getRequestMap () {
                    return null;
          }
     }



