import kw.team.plugin.setNamespace

plugins {
    id("cosmo.android.feature")
}

android {
    setNamespace("feature.main")
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.solving)
    implementation(projects.domain.subject)
    implementation(projects.core.designsystem)

    // android
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}
