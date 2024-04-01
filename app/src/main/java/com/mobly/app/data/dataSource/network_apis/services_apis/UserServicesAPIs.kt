package com.mobly.app.data.dataSource.network_apis.services_apis

import com.mobly.app.data.dataSource.network_apis.ApiConstants
import com.mobly.app.domain.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserServicesAPIs {
    @FormUrlEncoded
    @POST(ApiConstants.UserLogin)
    suspend fun userLogin(
        @Path("version") v: Int ,
        @Field("Username") userName: String,
        @Field("pass") password: String,
        @Query("culture") culture: String = "ar-sa"
    ): Response<LoginResponse>
}