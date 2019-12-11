# PokeMania EndPoints

#### **AuthDispatcher**

> **LOGIN**
* Method: POST
* Endpoint: /PokeManiaAPI/api/login  

> **INFO - Get info about current user sesson**
* Method: GET
* Endpoint: /PokeManiaAPI/api/info 

> **LOGOUT**
* Method: PUT
* Endpoint: /PokeManiaAPI/api/logout

#### **UserDispatcher**

> **CREATE USER**
* Method: POST
* Endpoint: /PokeManiaAPI/api/createuser?password={insert password here}  

> **ADD FRIEND**
* Method: POST
* Endpoint: /PokeManiaAPI/api/addfriend?friendusername={Insert friend username here}  

> **GET FRIENDS**
* Method: GET
* Endpoint: /PokeManiaAPI/api/getfriends?userid={insert the user ID}  

> **UPDATE USER VALUES**
* Method: PUT
* Endpoint: /PokeManiaAPI/api/updateuser

> **UPDATE COUNTER**
* Method: PUT
* Endpoint: /PokeManiaAPI/api/updatecounter

#### PokemonDispatcher

> **View All User's Pokemon (BOX)**
* Method: GET
* Endpoint: /PokeManiaAPI/api/pokemon?userId={Insert User's ID here}  

> **View User's Team**
* Method: GET
* Endpoint: /PokeManiaAPI/api/pokemonteam?userId={Insert User's ID here}

> **Update User's Team**
* Method: POST
* Endpoint: /PokeManiaAPI/api/pokemonteam?userId={Insert User's ID here}

> **Catch Pokemon**
* Method: POST
* Endpoint: /PokeManiaAPI/api/pokemon  

> **Release Pokemon**
* Method: DELETE
* Endpoint: /PokeManiaAPI/api/pokemon?pokemonId={pokemon's Id}


#### TradeRequestDispatcher

> **Create Trade Request**
* METHOD: POST
* Endpoint: /PokeManiaAPI/api/traderequest  

> **Update Trade Request / Whether an OFFER was Accepted or Declined**
* METHOD: PATCH
* Endpoint: /PokeManiaAPI/api/traderequest?status=Approved  

> **Update Trade Request to include an OFFER**
* METHOD: PATCH
* Endpoint /PokeManiaAPI/api/tradeoffer  

