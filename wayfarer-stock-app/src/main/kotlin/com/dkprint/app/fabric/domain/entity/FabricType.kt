package com.dkprint.app.fabric.domain.entity

enum class FabricType(val code: String, val description: String) {
    PET("01", "PET"),
    YANG_PET("02", "양PET"),
    OPP("03", "OPP"),
    YANG_OPP("04", "양OPP"),
    NON_GLOSS_OPP("05", "무광OPP"),
    NY("06", "NY"),
    YANG_NY("07", "양NY"),
    AL("08", "AL"),
    TRANSPARENT_LLD("09", "투명LLD"),
    OKTEN_LLD("10", "옥텐LLD"),
    UBACK_LLD("11", "유백LLD"),
    OKTEN_UBACK_LLD("12", "옥텐유백LLD"),
    DOUBLE_LAYER_PET("13", "증착PET"),
    CPP("14", "CPP"),
    DOUBLE_LAYER_CPP("15", "증착CPP"),
    EASY_CUT_LLD("16", "이지컷LLD"),
    EASY_PEEL_LLD("17", "이지필LLD"),
    CPR1("18", "CPR1"),
    CPR2("19", "CPR2"),
    CPR3("20", "CPR3"),
    CPR4("21", "CPR4"),
    DIRECT_INPUT("22", "직접입력");

    companion object {
        fun getByTypeName(typeName: String): FabricType {
            return entries.firstOrNull { it.name == typeName } ?: DIRECT_INPUT
        }
    }
}
