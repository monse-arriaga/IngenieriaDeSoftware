import MatchStatus from "./MatchStatus";
import MyStageSettings from "./MyStageSettings";
import MyStageType from "./MyStageType";
import ParticipantMatchGameResult from "./ParticipantMatchGameResult";
import ParticipantMatchResullt from "./ParticipantMatchResult";

interface MyMatchGame {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult: ParticipantMatchGameResult | null,
    opponentTwoResult: ParticipantMatchGameResult | null  ,
    stage: {
        id: number,
        tournamentId?: string,
        name?: string,
        type?: MyStageType,
        stageSettings?: MyStageSettings,
        number?: number
    },
    match: {
        id: number
        matchStatus?: MatchStatus,
        opponentOneResult?:ParticipantMatchResullt | null,
        opponentTwoResult?: ParticipantMatchResullt | null,
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
            number?: number
        },
        round?: {
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
        number?: number,
        childCount?: number
        },
    number: number
}

export default MyMatchGame