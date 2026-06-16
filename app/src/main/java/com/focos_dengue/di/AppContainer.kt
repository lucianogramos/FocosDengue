package com.focos_dengue.di

import android.content.Context
import com.focos_dengue.BuildConfig
import com.focos_dengue.data.remote.AuthDataSource
import com.focos_dengue.data.remote.ReportDataSource
import com.focos_dengue.data.remote.MapsLocationDataSource
import com.focos_dengue.data.repository.AuthRepositoryImpl
import com.focos_dengue.data.repository.LocationRepositoryImpl
import com.focos_dengue.data.repository.ReportRepositoryImpl
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage

class AppContainer(context: Context) {
    // Supabase Client
    private val supabaseClient = createSupabaseClient(supabaseUrl = BuildConfig.SUPABASE_URL, supabaseKey = BuildConfig.SUPABASE_KEY) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }

    // Data Sources
    private val authDataSource = AuthDataSource(supabaseClient.auth, supabaseClient.postgrest)
    private val reportDataSource = ReportDataSource(supabaseClient.postgrest)
    private val mapsLocationDataSource = MapsLocationDataSource(context)

    // Repositories
    val authRepository = AuthRepositoryImpl(authDataSource)
    val reportRepository = ReportRepositoryImpl(reportDataSource)
    private val locationRepository = LocationRepositoryImpl(mapsLocationDataSource)

    // Use Cases

}