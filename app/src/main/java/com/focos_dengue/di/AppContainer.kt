package com.focos_dengue.di

import android.content.Context
import android.location.Geocoder
import com.focos_dengue.BuildConfig
import com.focos_dengue.data.remote.AuthDataSource
import com.focos_dengue.data.remote.ImageDataSource
import com.focos_dengue.data.remote.ReportDataSource
import com.focos_dengue.data.repository.AuthRepositoryImpl
import com.focos_dengue.data.repository.GeoLocationRepositoryImpl
import com.focos_dengue.data.repository.ImageRepositoryImpl
import com.focos_dengue.data.repository.ReportRepositoryImpl
import com.focos_dengue.domain.service.ImageCompService
import com.focos_dengue.domain.usecase.GetAddressFromLatLngUseCase
import com.focos_dengue.domain.usecase.GetReportsUseCase
import com.focos_dengue.domain.usecase.SubmitReportUseCase
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import java.util.Locale

class AppContainer(context: Context) {
    // Supabase Client
    private val supabaseClient = createSupabaseClient(supabaseUrl = BuildConfig.SUPABASE_URL, supabaseKey = BuildConfig.SUPABASE_KEY) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }

    // Geocoder
    private val geocoder = Geocoder(context, Locale.getDefault())

    // Data Sources
    private val authDataSource = AuthDataSource(supabaseClient.auth, supabaseClient.postgrest)
    private val reportDataSource = ReportDataSource(supabaseClient.postgrest)
    private val imageDataSource = ImageDataSource(supabaseClient.storage, context)

    // Repositories
    val authRepository by lazy {
        AuthRepositoryImpl(authDataSource)
    }
    private val reportRepository by lazy {
        ReportRepositoryImpl(reportDataSource)
    }
    private val imageRepository by lazy {
        ImageRepositoryImpl(imageDataSource)
    }
    private val geoLocationRepository by lazy {
        GeoLocationRepositoryImpl(geocoder)
    }

    // Domain Services
    private val imageCompService by lazy {
        ImageCompService(context)
    }

    // Use Cases
    val submitReportUseCase by lazy {
        SubmitReportUseCase(reportRepository, imageRepository, imageCompService)
    }
    val getReportsUseCase by lazy {
        GetReportsUseCase(reportRepository, geoLocationRepository)
    }
    val getAddressFromLatLngUseCase by lazy {
        GetAddressFromLatLngUseCase(geoLocationRepository)
    }
}