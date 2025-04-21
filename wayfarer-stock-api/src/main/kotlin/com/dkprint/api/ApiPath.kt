package com.dkprint.api

object ApiPath {
    object Fabric {
        const val REGISTER = "/api/v1/fabric/register"
        const val GET_LIST = "/api/v1/fabric"
        const val GET_DETAILED_INFO = "/api/v1/fabric/{id}"
        const val SEARCH_BY_ORDERER = "/api/v1/fabric/search/vendor"
        const val SEARCH_BY_TYPE = "/api/v1/fabric/search/type"
        const val SEARCH_BY_CODE = "/api/v1/fabric/search/code"
        const val EDIT = "/api/v1/fabric/{id}"
        const val DELETE = "/api/v1/fabric/{id}"
        const val DELETE_MULTI = "/api/v1/fabrics"
        const val SET_USAGE_STATUS = "/api/v1/fabric/{id}/usage-status"
    }
}
