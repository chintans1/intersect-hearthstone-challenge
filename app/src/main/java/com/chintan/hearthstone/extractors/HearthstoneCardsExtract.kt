package com.chintan.hearthstone.extractors

import com.chintan.hearthstone.models.HearthstoneCard
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class HearthstoneCardsExtract : Extract<HearthstoneCard> {
    private val apiUrl = "https://omgvamp-hearthstone-v1.p.mashape.com/cards"
    // FIXME: hide key from plain sight in source code
    private val headers = Headers.Builder().add("X-Mashape-Key", "dSLmxAcwEJmshCQWvpoPPrrnSWF2p1fVyHljsn8MQDK2IH936l").build()

    // NOTE: Maybe it should be a Set<HearthstoneCard> to avoid duplicates unless that is wanted behaviour?
    override fun extract(): List<HearthstoneCard> {
        val allCardsJson = getAllCardsFromAPI()
        return convertToHearthstoneCards(allCardsJson)
    }

    private fun getAllCardsFromAPI(): String {
        val httpClient = OkHttpClient()
        val request = Request.Builder().url(apiUrl).headers(headers).get().build()

        val response = httpClient.newCall(request).execute()
        if (response?.body() != null) {
            val body = response.body()!!.string()
            response.body()!!.close()
            return body
        } else {
            // FIXME: Propagate this to the end user so they can know what went wrong
            throw RuntimeException("Failed to grab Hearthstone Cards from the API provider")
        }
    }

    private fun convertToHearthstoneCards(allCardsJson: String): List<HearthstoneCard> {
        val hearthstoneCards: MutableList<HearthstoneCard> = mutableListOf()
        val jsonObject = JSONObject(allCardsJson)

        jsonObject.keys().forEach { cardCategory ->
            hearthstoneCards.addAll(convertToHearthstoneCards(jsonObject.get(cardCategory) as JSONArray))
        }

        return hearthstoneCards
    }

    private fun convertToHearthstoneCards(cardsArray: JSONArray): List<HearthstoneCard> {
        val hearthstoneCards: MutableList<HearthstoneCard> = mutableListOf()

        for (i in 0 until cardsArray.length()) {
            hearthstoneCards.add(buildHearthstoneCard(cardsArray.getJSONObject(i)))
        }

        return hearthstoneCards
    }

    private fun buildHearthstoneCard(rawCard: JSONObject): HearthstoneCard {
        // Assuming that the API always returns objects with Name and Type defined
        return HearthstoneCard(
            rawCard.getString("name"),
            rawCard.getString("type"),
            rawCard.optString("playerClass", "No player class"),
            rawCard.optString("img")) // Empty string with there is no "img" key
    }
}
