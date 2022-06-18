# Cantosdeave
Android native (Kotlin) Simple App - API REST CONSUME training II
---
This app uses a free API REST JSON response to show results into a recyclerView with an adapter wich have a viewHolder class inside. 

Every row has a button that starts a new activity with the sound and location of the record on the map.

The views uses viewBinding.

The project uses coroutines to load the API REST information in a second thread. 
Project also implements MVVM arch.

The API REST response is made with retrofit.

(If clone, remember add your own MAPS_API_KEY in AndroidManifest.xml)
