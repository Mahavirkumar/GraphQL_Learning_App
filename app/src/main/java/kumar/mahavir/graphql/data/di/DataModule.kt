package kumar.mahavir.graphql.data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kumar.mahavir.graphql.data.repository.CountryRepoImpl
import kumar.mahavir.graphql.domain.repository.CountryRepo
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApolloClient():ApolloClient{
        val okHttpClient = OkHttpClient.Builder().build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
        return apolloClient
    }

    @Provides
    fun provideCountryRepo(apolloClient: ApolloClient):CountryRepo{
        return CountryRepoImpl(apolloClient)
    }


}