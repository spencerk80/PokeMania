//Trade model that matches the serverside Java model which holds relevent data about a trade offer
export class Trade {

    constructor(public id: number, public trainerID1: number, public trainerID2: number, public pokemonID1: number, public pokemonID2: number) {

    }

}