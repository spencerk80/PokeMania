//Pokemon model that matches the Java model from the server which holds relevent data about a pokemon
export class Pokemon {


    constructor(public id: number, public trainerId: number, public dexNum: number, public level: number, public hp: number, public att: number, 
                public def: number, public spd: number, public type1: string, public type2: string, public frontImg: string, public backImg: string) {

    }

}
