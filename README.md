# Pokemon Catcher App

Pokemon Catcher App is a simple app to explore all available Pokemons from Pokemon API. It uses (https://pokeapi.co) as API source.

## Features

- Explore all Pokemons
<img src="https://github.com/Tyzar/pokemons-catcher/assets/17083614/0c6a9c36-da7c-467f-b323-82e630c4cbde" width="200" >

- View Pokemon details
<img src="https://github.com/Tyzar/pokemons-catcher/assets/17083614/d72353cd-f81f-4802-a019-dfa48540bd90" width="200" >

- View all Pokemons that has been caught by you
<img src="https://github.com/Tyzar/pokemons-catcher/assets/17083614/c0b30d96-ee04-403b-bf07-4db02a1a0fe9" width="200" >
  
- Catch Pokemon with 50% probability rate at each try
- On catch success, you can give it a nickname
- Release Pokemon with random roll. If roll is prime number, then release success

Video of the app is here (https://shortlurl.com/pokemon_catcher_app)

## Technology
News App use uses these libraries:
- Jetpack Compose
- Lifecycle View Model
- Navigation Compose
- Coroutine & Flow
- Dagger Hilt
- Ktor Http Client

This project also use new version catalog management .toml to manage all dependency libraries versions.

## Architecture Overview
Architecture of project separates business logic from display (UI) logic. This implemented by structuring project with directories like below:
- core
- ---- utils
- ---- errors
- domain
- ---- usecases
- -------- usecase_1
- -------- usecase_n
- ---- entities
- ---- repositories
- -------- repository_1
- presentations
- ---- screens
- -------- shared_components
- -------- screen_1
- -------- screen_n
- ---- routes
- ---- themes
- ---- uistate
- -------- uistate_1
- ------------ view_state_1
- ------------ event_1
- ------------ view_models_1
- datasources
- ---- remotes
- -------- api_dir_1
- ------------ api_1
- ------------ request
- ------------ response
- ---- local
- -------- local_data_dir_1
- ------------ local_data_1
- di
- implementation
- ---- repositories
- -------- repository_1_impl
- ---- datasources
- ------- remote
- ----------- ktor
- --------------- api_1_impl
- ------- local
- ----------- room
- --------------- db
- --------------- tables
- --------------- local_data_1_dir
- ------------------- local_data_1_room_dao
- ------------------- local_data_1_impl

#### Core Layer
**Core**: Consists class or file that reusable at each directories or layers like utility class as example.
#### Domain Layer
**Domain**: Consists entity of data that can be used by presentation layer and data layer interface. Domain also contains usecases. Usecase used by presentation layer to trigger an action to data layer. Interface of repository also part of this layer.
#### Presentation Layer
**Presentations**: All presentations or UI related file are organized in this directories. Ex: Jetpack compose screens or components, viewmodels, routing, theme, and UI specific utility file
#### Data Layer
**Repositories**: Besides in domain layer, repository also has an implementations in this data layer to provide communication from domain to datasources.

**Datasources**: Responsible to fetch data from remote sources like web API or local sources (local database, preferences, file, etc).

## State Management
In this project, MVI(Model View Intent) pattern choosed as app state management. MVI implemented by three main class specifically ViewModel, Event class, and View State class. 
Event used to represent all action that can change View State. In Kotlin, thanks to Sealed Class, we can regroup and handle some related context events as one type.
```
sealed class DetailMyPokemonEvent {
    data class Load(val id: Int) :
        DetailMyPokemonEvent()

    data class Rename(val id: Int) :
        DetailMyPokemonEvent()
}
```

Meanwhile, State class is used to represents what state currently the screen in. Also, one key that must be noticed is state class must be an immutable class that can only be read. Using immutable class like data class, we can avoid direct modification of object outside viewmodels class.
```
data class DetailMyPokemonState(
    val isFirstLoad: Boolean = true,
    val isLoading: Boolean = false,
    val myPokemon: MyPokemon? = null,
    val error: AppError? = null,
    val renameResult: Either<AppError, Unit>? = null
)
```

For handling events and change view state as business logic expected, we use ViewModel. ViewModel cannot be affected by Android configuration changes, thus make it perfect place to store the view state. In this project, we use StateFlow to store state info and to emit state changes.
```
@HiltViewModel
class DetailMyPokemonVm @Inject constructor(
    private val myPokemonsRepository: MyPokemonsRepository,
    private val renamePokemon: RenamePokemon
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            DetailMyPokemonState()
        )
    val state = mState.asStateFlow()

    fun notify(event: DetailMyPokemonEvent) {
        when (event) {
            is DetailMyPokemonEvent.Load -> handleLoad(
                event
            )

            is DetailMyPokemonEvent.Rename -> handleRename()
        }
    }
  ...
}
```

And for consuming state in Compose screen or component, we use collectAsStateWithLifecycle extension method to convert it to Compose component State.
```
@Composable
fun DetailMyPokemonScreen(
    id: Int,
    navController: NavController,
    detailMyPokemonVm: DetailMyPokemonVm,
    myPokemonsVm: MyPokemonsVm
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val detailState by detailMyPokemonVm.state.collectAsStateWithLifecycle()

    LaunchedEffect(detailState.error) {
        if (detailState.error != null) {
            snackbarHostState.showSnackbar(
                detailState.error?.errMsg
                    ?: "An error occurred"
            )
        }
    }

    LaunchedEffect(detailState.isFirstLoad) {
        if (detailState.isFirstLoad) {
            detailMyPokemonVm.notify(
                DetailMyPokemonEvent.Load(
                    id
                )
            )
        }
    }
  ...
}
```
