import Match from "./Match";

interface Round {
    id: number,
    stage: {id: number},
    group: {id: number},
    number: number,
    matches: Array<Match>
}

export default Round