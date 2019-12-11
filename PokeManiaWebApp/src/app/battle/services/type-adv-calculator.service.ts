import { Injectable } from '@angular/core'
import { Type } from '../../enums/Type'
import { TypeAdv } from '../../enums/TypeAdv'
import { Pokemon } from 'src/app/models/Pokemon'

@Injectable({
  providedIn: 'root'
})
export class TypeAdvCalculatorService {

  constructor() { }

modifierCalc(atkr: Pokemon, dfndr: Pokemon): number {

  let attack: number
  let attack2: number 
  
  attack = this.advCalc(Type[atkr.type1.toUpperCase()], Type[dfndr.type1.toUpperCase()])

  if(dfndr.type2 != null)

    attack *= this.advCalc(Type[atkr.type1.toUpperCase()], Type[dfndr.type2.toUpperCase()])

  if(atkr.type2 != null) {

    attack2 = this.advCalc(Type[atkr.type2.toUpperCase()], Type[dfndr.type1.toUpperCase()])

    if(dfndr.type2 != null)

      attack2 *= this.advCalc(Type[atkr.type2.toUpperCase()], Type[dfndr.type2.toUpperCase()])

      attack = (attack + attack2) / 2

  }

  return attack

  /*
   * attack = 2 | 1 | 0.5 | 0.25
   * if defender has a second type then
   *    attack *= 2 | 1 | 0.5 | 0.25
   * if attacker has a second type then
   *    attack2 = 2 | 1 | 0.5 | 0.25
   *    if defender has a second type then
   *        attack2 *= 2 | 1 | 0.5 | 0.25
   *        attack = (attack + attack2) / 2
   * 
   * return attack //This is the type advantage multiplier ranging from 4 to 0.125
   */

}

private advCalc(atkr: Type, dfndr: Type): number {

  switch (atkr) {

    case Type.NORMAL:

      return this.attackerIsNormal(dfndr)

    case Type.FIGHTING:

      return this.attackerIsFighting(dfndr)

    case Type.FLYING:

      return this.attackerIsFlying(dfndr)

    case Type.POISON:

      return this.attackerIsPoison(dfndr)

    case Type.GROUND:

      return this.attackerIsGround(dfndr)

    case Type.ROCK:

      return this.attackerIsRock(dfndr)

    case Type.BUG:

      return this.attackerIsBug(dfndr)

    case Type.GHOST:

      return this.attackerIsGhost(dfndr)

    case Type.STEEL:

      return this.attackerIsSteel(dfndr)

    case Type.FIRE:

      return this.attackerIsFire(dfndr)

    case Type.WATER:

      return this.attackerIsWater(dfndr)

    case Type.GRASS:

      return this.attackerIsGrass(dfndr)

    case Type.ELECTRIC:

      return this.attackerIsElectric(dfndr)

    case Type.PSYCHIC:

      return this.attackerIsPsychic(dfndr)

    case Type.ICE:

      return this.attackerIsIce(dfndr)

    case Type.DRAGON:

      return this.attackerIsDragon(dfndr)

    case Type.DARK:

      return this.attackerIsDark(dfndr)

    case Type.FAIRY:

      return this.attackerIsFairy(dfndr)

  }

}

private attackerIsNormal(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:

      return TypeAdv.NORMAL

    case Type.ROCK:

      return TypeAdv.DISADV

    case Type.BUG:

      return TypeAdv.NORMAL

    case Type.GHOST:

      return TypeAdv.NOEFFECT

    case Type.STEEL:

      return TypeAdv.DISADV

    case Type.FIRE:
    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:
    case Type.DRAGON:
    case Type.DARK:
    case Type.FAIRY:

      return TypeAdv.NORMAL

  }

}

private attackerIsFighting(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

      return TypeAdv.ADV

    case Type.FIGHTING:

      return TypeAdv.NORMAL

    case Type.FLYING:
    case Type.POISON:

      return TypeAdv.DISADV

    case Type.GROUND:

      return TypeAdv.NORMAL

    case Type.ROCK:

      return TypeAdv.ADV

    case Type.BUG:

      return TypeAdv.DISADV

    case Type.GHOST:

      return TypeAdv.NOEFFECT

    case Type.STEEL:

      return TypeAdv.ADV

    case Type.FIRE:
    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:

      return TypeAdv.NORMAL

    case Type.PSYCHIC:

      return TypeAdv.DISADV

    case Type.ICE:

      return TypeAdv.ADV

    case Type.DRAGON:

      return TypeAdv.NORMAL

    case Type.DARK:

      return TypeAdv.ADV

    case Type.FAIRY:

      return TypeAdv.DISADV

  }

}

private attackerIsFlying(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

      return TypeAdv.NORMAL

    case Type.FIGHTING:

      return TypeAdv.ADV

    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:

      return TypeAdv.NORMAL

    case Type.ROCK:

      return TypeAdv.DISADV

    case Type.BUG:

      return TypeAdv.ADV

    case Type.GHOST:

      return TypeAdv.NORMAL

    case Type.STEEL:

      return TypeAdv.DISADV

    case Type.FIRE:
    case Type.WATER:

      return TypeAdv.NORMAL

    case Type.GRASS:

      return TypeAdv.ADV

    case Type.ELECTRIC:

      return TypeAdv.DISADV

    case Type.PSYCHIC:
    case Type.ICE:
    case Type.DRAGON:
    case Type.DARK:
    case Type.FAIRY:

      return TypeAdv.NORMAL

  }

}

private attackerIsPoison(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:

      return TypeAdv.NORMAL

    case Type.POISON:
    case Type.GROUND:
    case Type.ROCK:

      return TypeAdv.DISADV

    case Type.BUG:

      return TypeAdv.NORMAL

    case Type.GHOST:

      return TypeAdv.DISADV

    case Type.STEEL:

      return TypeAdv.NOEFFECT

    case Type.FIRE:
    case Type.WATER:

      return TypeAdv.NORMAL

    case Type.GRASS:

      return TypeAdv.ADV

    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:
    case Type.DRAGON:
    case Type.DARK:

      return TypeAdv.NORMAL

    case Type.FAIRY:

      return TypeAdv.ADV

  }

}

private attackerIsGround(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:

      return TypeAdv.NORMAL

    case Type.FLYING:

      return TypeAdv.NOEFFECT

    case Type.POISON:

      return TypeAdv.ADV

    case Type.GROUND:

      return TypeAdv.NORMAL

    case Type.ROCK:

      return TypeAdv.ADV

    case Type.BUG:

      return TypeAdv.DISADV

    case Type.GHOST:

      return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:

        return TypeAdv.ADV

    case Type.WATER:

        return TypeAdv.NORMAL

    case Type.GRASS:

        return TypeAdv.DISADV

    case Type.ELECTRIC:

        return TypeAdv.ADV

    case Type.PSYCHIC:
    case Type.ICE:
    case Type.DRAGON:
    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsRock(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NORMAL

    case Type.FIGHTING:

        return TypeAdv.DISADV

    case Type.FLYING:

        return TypeAdv.ADV

    case Type.POISON:

        return TypeAdv.NORMAL

    case Type.GROUND:

        return TypeAdv.DISADV

    case Type.ROCK:

        return TypeAdv.NORMAL

    case Type.BUG:

        return TypeAdv.ADV

    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:

        return TypeAdv.DISADV

    case Type.FIRE:

        return TypeAdv.ADV

    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:
    case Type.PSYCHIC:

        return TypeAdv.NORMAL

    case Type.ICE:

        return TypeAdv.ADV

    case Type.DRAGON:
    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsBug(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NORMAL

    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:

        return TypeAdv.DISADV

    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:

        return TypeAdv.NORMAL

    case Type.GHOST:
    case Type.STEEL:
    case Type.FIRE:

     return TypeAdv.DISADV

    case Type.WATER:

     return TypeAdv.NORMAL

    case Type.GRASS:

      return TypeAdv.ADV

    case Type.ELECTRIC:

      return TypeAdv.NORMAL

    case Type.PSYCHIC:

      return TypeAdv.ADV

    case Type.ICE:
    case Type.DRAGON:

      return TypeAdv.NORMAL

    case Type.DARK:

        return TypeAdv.ADV

    case Type.FAIRY:

        return TypeAdv.DISADV

  }

}

private attackerIsGhost(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NOEFFECT

    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:

        return TypeAdv.NORMAL

    case Type.GHOST:

        return TypeAdv.ADV

    case Type.STEEL:
    case Type.FIRE:
    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:

        return TypeAdv.NORMAL

    case Type.PSYCHIC:

        return TypeAdv.ADV

    case Type.ICE:
    case Type.DRAGON:

        return TypeAdv.NORMAL

    case Type.DARK:

        return TypeAdv.DISADV

    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsSteel(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:

        return TypeAdv.NORMAL

    case Type.ROCK:

        return TypeAdv.ADV

    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:
    case Type.WATER:

        return TypeAdv.DISADV

    case Type.GRASS:

        return TypeAdv.NORMAL

    case Type.ELECTRIC:

        return TypeAdv.DISADV

    case Type.PSYCHIC:

        return TypeAdv.NORMAL

    case Type.ICE:

        return TypeAdv.ADV

    case Type.DRAGON:
    case Type.DARK:

        return TypeAdv.NORMAL

    case Type.FAIRY:

        return TypeAdv.ADV

  }

}

private attackerIsFire(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:

        return TypeAdv.NORMAL

    case Type.ROCK:

      return TypeAdv.DISADV

    case Type.BUG:

        return TypeAdv.ADV

    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:

        return TypeAdv.ADV

    case Type.FIRE:
    case Type.WATER:

        return TypeAdv.DISADV

    case Type.GRASS:

        return TypeAdv.ADV

    case Type.ELECTRIC:
    case Type.PSYCHIC:

        return TypeAdv.NORMAL

    case Type.ICE:

        return TypeAdv.ADV

    case Type.DRAGON:

        return TypeAdv.DISADV

    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsWater(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:

        return TypeAdv.NORMAL

    case Type.GROUND:
    case Type.ROCK:

        return TypeAdv.ADV

    case Type.BUG:
    case Type.GHOST:
    case Type.STEEL:

        return TypeAdv.NORMAL

    case Type.FIRE:

        return TypeAdv.ADV

    case Type.WATER:
    case Type.GRASS:

        return TypeAdv.DISADV

    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:

        return TypeAdv.DISADV

    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsGrass(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:

        return TypeAdv.NORMAL

    case Type.FLYING:
    case Type.POISON:

        return TypeAdv.DISADV

    case Type.GROUND:
    case Type.ROCK:

        return TypeAdv.ADV

    case Type.BUG:

        return TypeAdv.DISADV

    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:

        return TypeAdv.DISADV

    case Type.WATER:

        return TypeAdv.ADV

    case Type.GRASS:

        return TypeAdv.DISADV

    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:

        return TypeAdv.DISADV

    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsElectric(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:

        return TypeAdv.NORMAL

    case Type.FLYING:

        return TypeAdv.ADV

    case Type.POISON:

        return TypeAdv.NORMAL

    case Type.GROUND:

        return TypeAdv.NOEFFECT

    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:
    case Type.STEEL:
    case Type.FIRE:

        return TypeAdv.NORMAL

    case Type.WATER:

        return TypeAdv.ADV

    case Type.GRASS:
    case Type.ELECTRIC:

        return TypeAdv.DISADV

    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:

        return TypeAdv.DISADV

    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsPsychic(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NORMAL

    case Type.FIGHTING:

        return TypeAdv.ADV

    case Type.FLYING:

        return TypeAdv.NORMAL

    case Type.POISON:

        return TypeAdv.ADV

    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:

        return TypeAdv.DISADV

    case Type.FIRE:
    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:

        return TypeAdv.NORMAL

    case Type.PSYCHIC:

        return TypeAdv.DISADV

    case Type.ICE:
    case Type.DRAGON:

        return TypeAdv.NORMAL

    case Type.DARK:

        return TypeAdv.NOEFFECT

    case Type.FAIRY:

        return TypeAdv.NOEFFECT

  }

}

private attackerIsIce(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:

        return TypeAdv.NORMAL

    case Type.FLYING:

        return TypeAdv.ADV

    case Type.POISON:

        return TypeAdv.NORMAL

    case Type.GROUND:

        return TypeAdv.ADV

    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:
    case Type.WATER:

        return TypeAdv.DISADV

    case Type.GRASS:

        return TypeAdv.ADV

    case Type.ELECTRIC:
    case Type.PSYCHIC:

        return TypeAdv.NORMAL

    case Type.ICE:

        return TypeAdv.DISADV

    case Type.DRAGON:

        return TypeAdv.ADV

    case Type.DARK:
    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsDragon(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:
    case Type.FIGHTING:
    case Type.FLYING:
    case Type.POISON:
    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:

        return TypeAdv.DISADV

    case Type.FIRE:
    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:

        return TypeAdv.ADV

    case Type.DARK:

        return TypeAdv.NORMAL

    case Type.FAIRY:

        return TypeAdv.NOEFFECT

  }

}

private attackerIsDark(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NORMAL

    case Type.FIGHTING:

        return TypeAdv.ADV

    case Type.FLYING:

        return TypeAdv.NORMAL

    case Type.POISON:

        return TypeAdv.DISADV

    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:

        return TypeAdv.DISADV

    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:
    case Type.DARK:

        return TypeAdv.ADV

    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

private attackerIsFairy(dfndr: Type): TypeAdv {

  switch (dfndr) {

    case Type.NORMAL:

        return TypeAdv.NORMAL

    case Type.FIGHTING:

        return TypeAdv.ADV

    case Type.FLYING:

        return TypeAdv.NORMAL

    case Type.POISON:

        return TypeAdv.DISADV

    case Type.GROUND:
    case Type.ROCK:
    case Type.BUG:
    case Type.GHOST:

        return TypeAdv.NORMAL

    case Type.STEEL:
    case Type.FIRE:

        return TypeAdv.DISADV

    case Type.WATER:
    case Type.GRASS:
    case Type.ELECTRIC:
    case Type.PSYCHIC:
    case Type.ICE:

        return TypeAdv.NORMAL

    case Type.DRAGON:
    case Type.DARK:

        return TypeAdv.ADV

    case Type.FAIRY:

        return TypeAdv.NORMAL

  }

}

}