
<p align="center">
  <h1 align="center">Auto Image Slider</h1>
</p>

<p align="center">
  <img src="https://media.suara.com/pictures/653x366/2020/06/01/61973-blackpink-soompi.jpg?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=80"/>
</p>
<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.1-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Koltin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/Fuadafdhal"><img src="https://img.shields.io/github/followers/Fuadafdhal?label=follow&style=social"></a>
</p>


## Screenshot
| Image Slider Default| Image Slider With Custom Adapter|
|---|---|
|![](assets/Example-1.gif)|![](assets/Example-2.gif)|


## Usage

-   Add ImageSlider to your **layout**

```xml
    <com.afdhal_fa.imageslider.ImageSlider
        android:id="@+id/imageSlide"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        app:iss_auto_cycle="true"
        app:iss_delay="0"
        app:iss_period="1000"/>
```
-   You can change placeholder image.

```xml
 app:iss_placeholder="@drawable/placeholder"
```
-   You can change error image.

```xml
 app:iss_error_image="@drawable/error"
```

-   You can change image scale type.

```xml
 app:iss_image_scaleType="centerCrop"
``` 
-   You can change title color

```xml
app:iss_title_color="@color/purple_500"
```
-   You can hide title 

```xml
app:iss_with_title="false"
```

-   You can hide background title 

```xml
app:iss_with_background="false"
```

-   You can change text gravity

```xml
app:iss_title_gravity="center|bottom"
```

-   You can change text title background

```xml
app:iss_title_background="@drawable/gradient"
```


<!-- -   You can change indicators.

```xml
app:iss_selected_dot="@drawable/default_selected_dot"
app:iss_unselected_dot="@drawable/default_unselected_dot"
```
 -->
 
 
 -   Add ImageSlider to your **Activity**

```kt
  val imageList = ArrayList<SlideUIModel>() 
  imageList.add(SlideUIModel("https://s.id/Ccoeo", "Blackpink - Jennie"))
  imageList.add(SlideUIModel("https://s.id/CcouZ", "Blackpink - Lisa"))
  imageList.add(SlideUIModel("https://s.id/CcoQ1", "Blackpink - Rose"))
  imageList.add(SlideUIModel("https://s.id/Cco-g", "Blackpink - Jisoo"))

  imageSlide.setImageList(imageList)
```

-   You can use click listener.

```kt
  imageSlide.setItemClickListener(object : ItemClickListener {
      override fun onItemClick(model: SlideUIModel, position: Int) {
          Toast.makeText(this@MainActivity, "${model.title}", Toast.LENGTH_SHORT).show()
      }
  })
```

-   You can add stop and start auto sliding again.

```kt
  imageSlide.startSliding(3000) // with new period
  imageSlide.startSliding()
  imageSlide.stopSliding()
```

-   You can make custom item slide with recyclerview adapter. You can see example adapter here <a href="https://github.com/Fuadafdhal/Auto-Image-Slider/blob/master/app/src/main/java/com/afdhal_fa/imagesliderlibarary/SampleAdapter.kt">Example Custom Adapter</a>
```kt
  val imageList = ArrayList<BannerUIModel>() // Create image list
  imageList.add(BannerUIModel("https://s.id/Ccoeo", "Blackpink - Jennie"))
  imageList.add(BannerUIModel("https://s.id/CcouZ", "Blackpink - Lisa"))
  imageList.add(BannerUIModel("https://s.id/CcoQ1", "Blackpink - Rose"))
  imageList.add(BannerUIModel("https://s.id/Cco-g", "Blackpink - Jisoo"))
  mAdapter.setItem(imageList)
  binding.imageSlide.setImageListWithAdapter(mAdapter, imageList.size)
  mAdapter.onItemClick = {  // user click listener adapter
      Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
   }
```

### Dependencies
 Add maven `jitpack.io` and `dependencies` in build.gradle (Project) :
 ```gradle
 // build.gradle project
 allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
 }
 
 // build.gradle app/module
 dependencies {
     ...
      implementation 'com.github.Fuadafdhal:Auto-Image-Slider:v1.0'
 }
 ```

## License
```
Copyright 2021 Muh Fuad Afdhal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
---
