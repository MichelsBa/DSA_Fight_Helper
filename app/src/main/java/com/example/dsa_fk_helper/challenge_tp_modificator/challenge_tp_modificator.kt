package com.example.dsa_fk_helper.challenge_tp_modificator

import com.example.dsa_fk_helper.R
import com.example.dsa_fk_helper.databinding.ActivityMainBinding

data class Modificators (var difficulty : Int, var damage : Int)

 fun calculateFkMods(binding : ActivityMainBinding){
    val fkMods = getStartingValues(binding.inputDifficultyEditText.text.toString(), binding.inputDifficultyEditText.text.toString())

    //Check target movement and assign difficulty change
     when(binding.targetMovementOptionsRadioGroup.checkedRadioButtonId){
        R.id.target_no_movement -> fkMods.difficulty += 2
        R.id.target_fast_movement -> fkMods.difficulty += -2
        R.id.target_evading_movement -> fkMods.difficulty += -4
    }
     //Check player movement and assign difficulty change
    when(binding.playerMovementOptionsRadioGroup.checkedRadioButtonId){
        R.id.player_slow_movement -> fkMods.difficulty += -2
        R.id.player_normal_movement -> fkMods.difficulty += -4
        R.id.player_riding_slow -> fkMods.difficulty += -4
        R.id.player_riding_medium -> fkMods.difficulty += -6//ToDO: Change so a roll of 1d20 with result 1 is required to hit
        R.id.player_riding_fast -> fkMods.difficulty += -8
    }
     //Check target size and assign difficulty change
    when(binding.targetSizeRadioGroup.checkedRadioButtonId){
        R.id.target_size_tiny -> fkMods.difficulty += -8
        R.id.target_size_small -> fkMods.difficulty += -4
        R.id.target_size_big -> fkMods.difficulty += 4
        R.id.target_size_giant -> fkMods.difficulty += 8
    }
     //Check distance to target and assign difficulty and damage change
     if(binding.distanceRadioGroup.checkedRadioButtonId == R.id.distance_close){
         fkMods.difficulty += 2
         fkMods.damage += 1
     } else if (binding.distanceRadioGroup.checkedRadioButtonId == R.id.distance_far){
         fkMods.difficulty += -2
         fkMods.damage += -1
     }
     //Check visibility conditions for target and assign difficulty change
     when(binding.visibilityRadioGroup.checkedRadioButtonId){
         R.id.visibility_impared -> fkMods.difficulty += -2
         R.id.visibility_foggy -> fkMods.difficulty += -4
         R.id.visibility_low -> fkMods.difficulty += -6
         R.id.visibility_invisible -> fkMods.difficulty += -10 //ToDO: Change so a roll of 1d20 with result 1 is required to hit
     }
     //Check if the shot is targeted into an ongoing fight
     when(binding.shotIntoFightRadioGroup.checkedRadioButtonId){
         R.id.shot_into_fight_yes -> fkMods.difficulty += -2
     }
     //Check if player aimed and how long and assign difficulty change
     when(binding.aimingRadioGroup.checkedRadioButtonId){
         R.id.aiming_short -> fkMods.difficulty += 2
         R.id.aiming_long -> fkMods.difficulty += 4
     }

     binding.outputDifficulty.text = fkMods.difficulty.toString()
     binding.outputDamage.text = fkMods.damage.toString()
}

//Function to initialize modificator dataclass from input views
private fun getStartingValues (stringChallengeMod : String, stringTpMod : String): Modificators{
    var challengeMod = stringChallengeMod.toIntOrNull()
    var tpMod = stringTpMod.toIntOrNull()

    //if challengeMod or tpMod are not given integer Values use 0 in initializing
    if(challengeMod == null) challengeMod = 0
    if(tpMod == null) tpMod = 0

    return Modificators(challengeMod, tpMod)
}