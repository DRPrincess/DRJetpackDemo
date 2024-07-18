package com.dr.jetpackdemo.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * 功能：
 * 作者： duanrui
 * 时间： 2024/7/18
 */

class Repo {
    // 定义 Repo 类的属性（根据实际的 JSON 响应）
    var id: Int = 0
    var name: String = ""
}

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    fun listRepos1(@Path("user") user: String): Call<List<String>>
}
