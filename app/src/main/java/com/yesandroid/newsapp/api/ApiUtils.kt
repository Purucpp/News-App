package com.yesandroid.newsapp.api

import org.json.JSONArray
import org.json.JSONObject

class ApiUtils {

    companion object{
        fun isPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                true
            } else {
                false
            }
        }

        fun isStringPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is String
            } else {
                false
            }
        }

        fun isBooleanPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is Boolean
            } else {
                false
            }
        }

        fun isIntPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is Int
            } else {
                false
            }
        }



        fun isDoublePresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is Double
            } else {
                false
            }
        }

        fun isJsonObjectPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is JSONObject
            } else {
                false
            }
        }

        fun isJsonArrayPresent(jsonObject: JSONObject, name: String?): Boolean {
            return if (jsonObject.has(name) && !jsonObject.isNull(name)) {
                val value=jsonObject.get(name)
                value is JSONArray
            } else {
                false
            }
        }

    }
}