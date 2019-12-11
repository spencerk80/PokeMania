import { Pokemon } from './Pokemon'

export class BattleTurn {

    private _attacker: Pokemon
    private _defender: Pokemon
    private _damage: number

    constructor(attacker: Pokemon, defender: Pokemon, damage: number) {

        this._attacker = attacker
        this._defender = defender
        this._damage = damage

    }

    get attacker(): Pokemon { return this._attacker }
    get defender(): Pokemon { return this._defender }
    get damage(): number { return this._damage }

}