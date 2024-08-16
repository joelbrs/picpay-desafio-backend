import { UserTypeEnum } from "@/domain/models";
import { FindPayerPayeeById } from "@/domain/use-cases";

export class FindPayerPayeeByIdSpy implements FindPayerPayeeById {
    id?: number;
    user?: FindPayerPayeeById.Result;

    async findPayerPayeeById(
        payerId: number,
        _: number
    ): Promise<FindPayerPayeeById.Result> {
        const user = {
            cpf: "valid_cpf",
            email: "valid_email",
            id: payerId,
            nome: "valid_nome",
            userType: UserTypeEnum.LOJISTA,
        };

        this.id = user.id;
        this.user = user;
        return user;
    }
}
