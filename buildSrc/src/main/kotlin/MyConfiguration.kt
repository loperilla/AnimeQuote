object MyConfiguration  {
    const val baseProjectName = "com.loperilla"
    const val configCompileSdkVersion = 33
    const val configTargetSdkVersion = 33
    const val configMinSdkVersion = 26
    const val myApplicationIdConfig = "$baseProjectName.composeanime"
    const val configVersionCode = 1
    const val configVersionName = "1.0"

    object Modules {
        const val COREUI = ":core-ui"
        const val DATA = ":data"
        const val DATASOURCE = ":datasource"
        const val ONBOARDING = ":onboarding"
        const val ONB_DOMAIN = ":onboarding:onboarding_domain"
        const val ONB_PRESENTATION = ":onboarding:onboarding_presentation"
        const val MODEL = ":model"
    }

    object MAP_MODULES {
        val MODEL = mapOf("path" to Modules.MODEL)
        val DOMAIN = mapOf("path" to Modules.ONB_DOMAIN)
        val DATASOURCE = mapOf("path" to Modules.DATASOURCE)
    }
}