package com.chintan.hearthstone.extractors

interface Extract<T> {
    /**
     * Extract a certain type of information and parse it into a list of POJOs
     *
     * @return a list of POJOs where each object represents some object in the API response
     */
    fun extract(): List<T>
}