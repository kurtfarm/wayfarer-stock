object ApiPath {
    object Fabric {
        const val REGISTER_FABRIC = "/api/v1/fabric/register"
        const val READ_FABRIC_LIST = "/api/v1/fabric"
        const val READ_DETAILED_FABRIC_INFO = "/api/v1/fabric/{id}"
        const val READ_FABRIC_BY_ORDERER = "/api/v1/fabric/search/vendor"
        const val READ_FABRIC_BY_TYPE = "/api/v1/fabric/search/type"
        const val EDIT_FABRIC = "/api/v1/fabric/{id}"
        const val DELETE_FABRIC = "/api/v1/fabric/{id}"
    }
}
