import { CheckBalance } from "@/domain/use-cases";

export class CheckBalanceSpy implements CheckBalance {
    userId: number = 0;
    value: number = 0;
    result = true;

    async checkByUserId(id: number, val: number): Promise<CheckBalance.Result> {
        this.userId = id;
        this.value = val;
        return this.result;
    }
}
