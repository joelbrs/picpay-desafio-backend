import { FindPayerPayeeById } from "@/domain/use-cases";

export class FindPayerPayeeByIdSpy implements FindPayerPayeeById {
    payeeId?: number;
    payerId?: number;
    exists: boolean = true;

    async findPayerPayeeById(payerId: number, payeeId: number) {
        this.payeeId = payeeId;
        this.payerId = payerId;
        this.exists = true;

        return this.exists;
    }
}
