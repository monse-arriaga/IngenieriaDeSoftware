import { CrudInterface, Table } from "brackets-manager";
import StageService from "../services/StageService";
import { Group, Match, MatchGame, Round, Stage, Participant } from "brackets-model";
import GroupService from "../services/GroupService";
import RoundService from "../services/RoundService";
import MatchGameService from "../services/MatchGameService";
import MatchService from "../services/MatchService";
import ParticipantService from "../services/ParticipantService";

export class tournamentStorage implements CrudInterface {

    async insert<T>(table: Table, value: T): Promise<number>;
    async insert<T>(table: T, values: T[]): Promise<boolean>;
    
    async insert<T>(table: Table, values: T | T[]): Promise<number | boolean> {
        const isArray = Array.isArray(values)
        try {
            switch (table) {
                case "stage":
                    return await StageService.create(values as Stage | Stage[]).then()
                case "group":
                    return await GroupService.create(values as Group | Group[]).then()
                case "round":
                    return await RoundService.create(values as Round | Round[]).then()
                case "match":
                    return await MatchService.create(values as Match | Match[]).then()
                case "match_game":
                    return await MatchGameService.create(values as MatchGame | MatchGame[]).then()
                case "participant":
                    return await ParticipantService.create(values as Participant | Participant[]).then()
                default:
                    console.log("Unknown table");
                    return 0
            }
        } catch (error) {
            return isArray ? false : -1;
        }
    }

    async select<T>(table: Table): Promise<T[] | null>;
    async select<T>(table: Table, id: number): Promise<T | null>;
    async select<T>(table: Table, filter: Partial<T>): Promise<T[] | null>;

    async select<T>(table: Table, filter?: number | Partial<T>): Promise<T | T[] | null> {
        try {
            switch (table) {
                case "stage":
                    return await StageService.select(filter) as any;
                case "group":
                    return await GroupService.select(filter) as any;
                case "round":
                    return RoundService.select(filter) as any;
                case "match":
                    return await MatchService.select(filter) as any;
                case "match_game":
                    return await MatchGameService.select(filter) as any;
                case "participant":
                    return await ParticipantService.select(filter) as any;
                default:
                    return null;
            }
        } catch (error) {
            return null;
        }
    }

    async update<T>(table: Table, id: number, value: T): Promise<boolean>;
    async update<T>(table: Table, filter: Partial<T>, value: Partial<T>): Promise<boolean>;
 
    async update<T>(table: Table, filter: number | Partial<T>, value: T | Partial<T>): Promise<boolean> {
        try {
            switch (table) {
                case "stage":
                    await StageService.update(filter, value as Stage).then()
                    break;
                case "group":
                    await GroupService.update(filter, value as Group)
                    break;
                case "round":
                    await RoundService.update(filter, value as Round)
                    break;
                case "match":
                    await MatchService.update(filter, value as Match)
                    break;
                case "match_game":
                    await MatchGameService.update(filter, value as MatchGame)
                    break;
                case "participant":
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return false;
        }
        return true;    
    }

    async delete<T>(table: Table): Promise<boolean>;
    async delete<T>(table: Table, filter: Partial<T>): Promise<boolean>;
    
    async delete<T>(table: Table, filter?: T | Partial<T>): Promise<boolean> {
        try {
            switch (table) {
                case "stage":
                    await StageService.delete(filter as object)
                    break;
                case "group":
                    await GroupService.delete(filter as object)
                    break;
                case "round":
                    await RoundService.delete(filter as object)
                    break;
                case "match":
                    await MatchService.delete(filter as object)
                    break;
                case "match_game":
                    await MatchGameService.delete(filter as object)
                    break;
                case "participant":
                    await ParticipantService.delete(filter as object)
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return false;
        }
        return true;  
    }


}

export default tournamentStorage