import { CrudInterface, Table } from "brackets-manager";
import StageService from "../services/StageService";
import { Group, Match, MatchGame, Round, Stage } from "brackets-model";
import GroupService from "../services/GroupService";
import RoundService from "../services/RoundService";
import MatchGameService from "../services/MatchGameService";
import MatchService from "../services/MatchService";
import ParticipantService from "../services/ParticipantService";
import Participant from "../types/Participant";

export class tournamentStorage implements CrudInterface {


    public insert<T>(table: Table, value: T): Promise<number>;
    public insert<T>(table: T, values: T[]): Promise<boolean>;
    
    public async insert<T>(table: Table, values: T | T[]): Promise<number | boolean> {
        const isArray = Array.isArray(values)
        const valuesA = isArray ? values : [values]
        try {
            switch (table) {
                case "stage":
                    await StageService.create(valuesA as Stage[]).then()
                    break;
                case "group":
                    await GroupService.create(valuesA as Group[]).then()
                    break;
                case "round":
                    await RoundService.create(valuesA as Round[]).then()
                    break;
                case "match":
                    await MatchService.create(valuesA as Match[]).then()
                    break;
                case "match_game":
                    await MatchGameService.create(valuesA as MatchGame[]).then()
                    break;
                case "participant":
                    await ParticipantService.create(valuesA as Participant[]).then()
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return isArray ? false : -1;
        }
        return isArray ? true : 1;
    }

    public select<T>(table: Table): Promise<T[] | null>;
    public select<T>(table: Table, id: number): Promise<T | null>;
    public select<T>(table: Table, filter: Partial<T>): Promise<T[] | null>;

    public async select<T>(table: Table, filter?: number | Partial<T>): Promise< T |  T[] | null> {
        try {
            switch (table) {
                case "stage":
                    return await StageService.select(filter) as T[];
                case "group":
                    return await GroupService.select(filter) as T[];
                case "round":
                    return await RoundService.select(filter) as T[];
                case "match":
                    return await MatchService.select(filter) as T[];
                case "match_game":
                    return await MatchGameService.select(filter) as T[];
                case "participant":
                    return await ParticipantService.select(filter) as T[];
                default:
                    return null;
            }
        } catch (error) {
            return null;
        }
    }

    public update<T>(table: Table, id: number, value: T): Promise<boolean>;
    public update<T>(table: Table, filter: Partial<T>, value: Partial<T>): Promise<boolean>;
 
    public async update<T>(table: Table, filter: number | Partial<T>, value: T | Partial<T>): Promise<boolean> {
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

    public delete<T>(table: Table): Promise<boolean>;
    public delete<T>(table: Table, filter: Partial<T>): Promise<boolean>;
    
    public async delete<T>(table: Table, filter?: T | Partial<T>): Promise<boolean> {
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