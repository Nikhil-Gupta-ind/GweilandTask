package com.nikhil.gweilandtask.model

data class CurrencyInfoResponse(
    val `data`: Data,
    val status: Status
) {
    data class Data(
        val `1`: X1
    ) {
        data class X1(
            val category: String,
            val contract_address: List<Any>,
            val date_added: String,
            val date_launched: String,
            val description: String,
            val id: Int,
            val infinite_supply: Boolean,
            val is_hidden: Int,
            val logo: String,
            val name: String,
            val notice: String,
            val platform: Any,
            val self_reported_circulating_supply: Any,
            val self_reported_market_cap: Any,
            val self_reported_tags: Any,
            val slug: String,
            val subreddit: String,
            val symbol: String,
//            val tag-groups: List<String>,
//            val tag-names: List<String>,
            val tags: List<String>,
            val twitter_username: String,
            val urls: Urls
        ) {
            data class Urls(
                val announcement: List<Any>,
                val chat: List<Any>,
                val explorer: List<String>,
                val facebook: List<Any>,
                val message_board: List<String>,
                val reddit: List<String>,
                val source_code: List<String>,
                val technical_doc: List<String>,
                val twitter: List<Any>,
                val website: List<String>
            )
        }
    }

    data class Status(
        val credit_count: Int,
        val elapsed: Int,
        val error_code: Int,
        val error_message: Any,
        val notice: Any,
        val timestamp: String
    )
}