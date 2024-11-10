import kw.team.plugin.setNamespace

plugins {
    id("cosmo.android.feature")
    alias(libs.plugins.kotlin.serialization)

}

android {
    setNamespace("feature.solving")
}

dependencies {
    implementation(projects.domain.assesment)
    implementation(projects.domain.subject)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.immutable)
}