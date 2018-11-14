# Hearthstone Cards
This is a simple Android app built using Kotlin. It makes GET requests to this [API](https://market.mashape.com/omgvamp/hearthstone) and parses the JSON response to display all the Hearthstone cards in existence. It also allows users to filter through the cards by searching through the card names.

### Install
You can find the APK [here](hearthstone-cards.apk) and sideload it onto your Android device easily.

### Libraries Used
- [OkHttp](https://github.com/square/okhttp) for making API requests
- [Picasso](https://github.com/square/picasso) for grabbing the images for the Hearthstone cards

### Issues
- NPE when user tries to search before the cards are loaded
- Cards sort of take awhile to initially load due to the API response being very large in size
    - in the future, I would like to add some sort of message if the API request fails so the user can know what went wrong

### Credits
- [Hearthstone Card Back image](http://hearthstone.wikia.com/wiki/Card_Back?file=Card_back-Default.png)
- [Hearthstone App Icon](https://www.deviantart.com/mauriliosm/art/honeycomb-icon-Hearthstone-746949690)
