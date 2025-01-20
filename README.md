# MovieMash  

![MovieMash](/app/src/main/ic_launcher-playstore.png)  

--app link : [MovieMash](/app/release/app-release.apk)

---

## 🎥 About the Project  
**MovieMash** is an engaging app designed to fetch and display **movies** and **TV show** data. It includes features to toggle or navigate between movies and TV shows seamlessly, and a **details screen** for any selected item from the list.  

### 🛠️ Features  
- **Home Screen**: Toggle between movies and TV shows with ease.  
- **Details Screen**: View detailed information about a selected movie or TV show.  
- **Shimmer Effect**: Elegant shimmer loading effect while fetching data.  
- **Pull-to-Refresh**: Refresh content effortlessly when needed.  
- **Retry Option**: Retry fetching data in case of a failed retrieval.  

---

## 🚀 Technologies Used  
This project leverages the **MVVM architecture** and integrates modern libraries and tools for an optimized experience:  
- **Watchmode API**: Free version with a limit of 1000 requests/month.  
- **Koin**: Dependency injection.  
- **Retrofit**: Networking library for seamless API calls.  
- **Single.zip**: Combining multiple API requests into one.  

---

## 🧗 Challenges Faced & Solutions  
### 🌟 **Shimmer Effect**  
**Challenge**: Implementing shimmer loading was a new experience.  
**Solution**: Initially, I considered animations, but I discovered **Valentik's shimmer library**, which was ideal for my requirements.  

### 🌟 **Koin Integration**  
**Challenge**: New to **Koin**, I struggled with version compatibility and implementation.  
**Solution**: I referred to **Koin's official documentation** and YouTube tutorials to overcome the hurdles.  

### 🌟 **Single.zip Usage**  
**Challenge**: Understanding and using **Single.zip** for combining multiple API requests.  
**Solution**: Explored its purpose and implementation online, learning how it simplifies handling simultaneous API calls.  

### 🌟 **Limited API Access**  
**Challenge**: The free API version restricted access to certain features like fetching movie posters.  
**Solution**: Adapted to display **titles** instead of posters for grid items, reducing the API load. Future improvements will address this limitation.  

---

## 🖼️ App Workflow  
1. **Home Screen**: A grid layout displays the list of movies and TV shows.  
2. **Details Screen**: Displays details such as the **poster, release date, title, and description** of the selected item.  
3. **Logo**: Created using **Meta AI** to add a professional touch.  

---

## 🤝 Acknowledgements  
This project was a fantastic learning experience! I’m grateful for resources like:  
- **Google**  
- **ChatGPT**  
- **YouTube tutorials**  
- **Koin documentation**  

---

## 📌 Key Takeaways  
- Improved my knowledge of **dependency injection** with **Koin**.  
- Gained hands-on experience with **Single.zip** for managing multiple requests.  
- Successfully implemented the **shimmer effect** for the first time.  

---

### 🔗 Future Improvements  
- Explore premium API features for fetching **movie posters**.  
- Add more details to the **Details Screen**.  
- Enhance the app’s UI/UX for a more polished experience.  

---

### 👩‍💻 Author  
**[Krishna Jeena]**  
A passionate developer always eager to learn and create! 😊
