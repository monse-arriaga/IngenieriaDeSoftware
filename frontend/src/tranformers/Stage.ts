import { SeedOrdering, Stage, StageSettings, StageType } from "brackets-model";
import MyStage from "../types/MyStage";
import MyStageType from "../types/MyStageType";
import MyStageSettings from "../types/MyStageSettings";
import RoundRobin from "./RoundRobin";
import GrandFinal from "./GrandFinal";


class StageT {
    to (value:  MyStage): Stage {
        const mySettings = value.stageSettings;
        const settings:StageSettings = {
            size: mySettings.size,
            balanceByes: mySettings.balanceByes,
            matchesChildCount: mySettings.matchesChildCount,
            groupCount: mySettings.groupCount,
            roundRobinMode: RoundRobin.to(mySettings.roundRobinMode),
            consolationFinal: mySettings.consolationFinal,
            skipFirstRound: mySettings.skipFirstRound,
            grandFinal: GrandFinal.to(mySettings.grandFinal),
            seedOrdering: mySettings.seedOrdering as SeedOrdering[]
        }

        return {
            id: value.id,
            tournament_id: value.tournamentId,
            name: value.name,
            type: value.type  as StageType,
            number: value.number,
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
            roundRobinMode: RoundRobin.from(bSettings.roundRobinMode),
            consolationFinal: bSettings.consolationFinal,
            seedOrdering: bSettings.seedOrdering,
            skipFirstRound: bSettings.skipFirstRound,
            grandFinal: GrandFinal.from(bSettings.grandFinal),
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