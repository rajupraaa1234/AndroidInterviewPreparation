🚀 Caching Strategy

This project implements a multi-level caching mechanism to improve application performance, reduce unnecessary network requests, and provide offline support.

The repository follows the cache lookup order below:

      Memory Cache
            │
            ▼
      Room Database
            │
            ▼
      Remote API


When data is requested:

Check the Memory Cache.
If not available, check the Room Database.
If not available locally, fetch data from the Remote API.
Save the fresh data into Room and Memory Cache.
Return the data to the UI.

This approach minimizes network calls while ensuring the application continues to work even after app restarts or when the device is offline.


🧠 Memory Cache

The application uses an in-memory cache to avoid repeated database and network calls during the application's lifecycle.

Why?
Fastest data access
Eliminates unnecessary Room queries
Reduces API calls
Improves UI responsiveness
Implementation
Implemented using ConcurrentHashMap
Exposed through a generic MemoryCache interface
Injected using Hilt
Cleared automatically when the application process is killed


      Repository
            │
            ▼
      Memory Cache
            │
       ┌────┴────┐
       │         │
      Hit      Miss
       │         │
       ▼         ▼
      Return   Check Room


💾 Room Cache (Offline Support)

Room is used as a persistent cache to provide offline access and survive application restarts.

Why?
Data persists even after the app is closed
Supports offline usage
Reduces unnecessary API requests
Acts as the second level of cache


      Repository
            │
            ▼
      Room Database
            │
       ┌────┴────┐
       │         │
      Hit      Miss
       │         │
       ▼         ▼
      Return   Call API

After a successful API response:

Response DTO is mapped to the domain model.
Data is saved into Room.
Data is also stored in Memory Cache.
Updated data is returned to the UI.


🔄 Complete Data Flow

                      ViewModel
                          │
                          ▼
                   UserRepository
                          │
              ┌───────────┼───────────┐
              │           │           │
              ▼           ▼           ▼
       Memory Cache    Room DB    Remote API
              │           │           │
              └───────────┼───────────┘
                          │
                          ▼
                    Domain Model
                          │
                          ▼
                       UI State

                 📦 Data Transformation

The project follows Clean Architecture by separating network, database, and domain models.
      
      Remote API
            │
            ▼
      UsersData (DTO)
            │
            ▼
      Mapper
            │
            ▼
      Users (Domain)
            │
            ├─────────────► UI
            │
            ▼
      Mapper
            │
            ▼
      UserEntity
            │
            ▼
      Room Database

When reading from Room:

      Room Database
            │
            ▼
      UserEntity
            │
            ▼
      Mapper
            │
            ▼
      Users (Domain)
            │
            ▼
      Repository
      
      ⚙️ Repository Flow
      
      Request Data
            │
            ▼
      Check Memory Cache
            │
       ┌────┴────┐
       │         │
      Hit      Miss
       │         │
       ▼         ▼
      Return   Check Room
                    │
             ┌──────┴──────┐
             │             │
           Hit           Miss
             │             │
             ▼             ▼
      Memory Cache      Remote API
      Update                │
             │              ▼
             │        DTO → Domain
             │              │
             │              ▼
             └────── Save Room
                           │
                           ▼
                   Save Memory Cache
                           │
                           ▼
                       Return Data

                 
