package com.foco_acessibilidade_dengue.data.remote
import com.foco_acessibilidade_dengue.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage



val supabase = createSupabaseClient(supabaseUrl = BuildConfig.SUPABASE_URL, supabaseKey = BuildConfig.SUPABASE_KEY) {
    install(Auth)
    install(Postgrest)
    install(Storage)
}