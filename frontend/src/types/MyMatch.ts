import MatchStatus from "./MatchStatus";
import MyStageSettings from "./MyStageSettings";
import MyStageType from "./MyStageType";
import ParticipantMatchResullt from "./ParticipantMatchResult";

interface MyMatch {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult:ParticipantMatchResullt | null,
    opponentTwoResult: ParticipantMatchResullt | null,
    stage: {
        id: number,
        tournamentId?: string,
        name?: string,
        type?: MyStageType,
        stageSettings?: MyStageSettings,
        number?: number
    },
    group: {
        id: number,
        stage?: {
            id: number,
            tournamentId?: string,
            name?: string,
            type?: MyStageType,
            stageSettings?: MyStageSettings,
            number?: number
        },
        number?: number
    },
    round: {
        id: number,
        stage?: {
            id: number,
            tournamentId?: string,
            name?: string,
            type?: MyStageType,
            stageSettings?: MyStageSettings,
            number?: number
        },
        group?: {
            id: number,
            stage?: {
                id: number,
                tournamentId?: string,
                name?: string,
                type?: MyStageType,
                stageSettings?: MyStageSettings,
                number?: number
            },
            number?: number,
        },
        number?: number
    },
    number: number,
    childCount: number
}

export default MyMatch