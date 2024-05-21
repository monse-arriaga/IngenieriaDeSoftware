import { GrandFinalType, RoundRobinMode, Stage, StageSettings, StageType } from "brackets-model";
import MyStage from "../types/MyStage";
import MyStageType from "../types/MyStageType";
import MyStageSettings from "../types/MyStageSettings";
import MyRoundRobinMode from "../types/MyRoundRobinMode";
import MyGrandFinalType from "../types/MyGrandFinalType";

class StageT {
    to (value:  MyStage): Stage {
        const mySettings = value.stageSettings;
        const settings:StageSettings = {
            size: mySettings.size,
            balanceByes: mySettings.balanceByes,
            matchesChildCount: mySettings.matchesChildCount,
            groupCount: mySettings.groupCount,
            roundRobinMode: mySettings.roundRobinMode as RoundRobinMode,
            consolationFinal: mySettings.consolationFinal,
            skipFirstRound: mySettings.skipFirstRound,
            grandFinal: mySettings.grandFinal as GrandFinalType
        }

        return {
            id: value.id,
            tournament_id: value.tournamentId,
            name: value.name,
            type: value.type  as StageType,
            number: 1,
            settings: settings
        }
    }

    from (value: Stage): MyStage {
        const bSettings = value.settings;
        const settigns:MyStageSettings = {
            size: bSettings.size,
            balanceByes: bSettings.balanceByes,
            matchesChildCount: bSettings.matchesChildCount,
            groupCount: bSettings.groupCount,
            roundRobinMode: bSettings.roundRobinMode as MyRoundRobinMode,
            consolationFinal: bSettings.consolationFinal,
            skipFirstRound: bSettings.skipFirstRound,
            grandFinal: bSettings.grandFinal as MyGrandFinalType,
        }

        return {
            id: value.id as number,
            tournamentId: value.tournament_id.toString(),
            name: value.name,
            type: value.type as MyStageType,
            stageSettings: settigns,
            number: value.number
        }
    }
}

export default StageT