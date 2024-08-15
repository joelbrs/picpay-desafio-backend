import { CreateTransactionController } from "@/presentation/controllers";
import { Validation } from "@/presentation/protocols";

const makeValidationStub = (): Validation => {
    class ValidationStub implements Validation {
        validate(_: any) {
            return null;
        }
    }

    return new ValidationStub();
};

const makeSut = () => {
    const validationStub = makeValidationStub();
    const sut = new CreateTransactionController(validationStub);

    return { sut, validationStub };
};

describe("Create Transaction Controller", () => {
    it("should returns 400 if no value is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payee: 1,
            payer: 1,
        };

        jest.spyOn(validationStub, "validate").mockReturnValueOnce(
            new Error("Missing Param: value")
        );

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new Error("Missing Param: value"));
    });

    it("should returns 400 if no payer is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payee: 1,
            value: 100.0,
        };

        jest.spyOn(validationStub, "validate").mockReturnValueOnce(
            new Error("Missing Param: payer")
        );

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new Error("Missing Param: payer"));
    });

    it("should returns 400 if no payee is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payer: 1,
            value: 100.0,
        };

        jest.spyOn(validationStub, "validate").mockReturnValueOnce(
            new Error("Missing Param: payee")
        );

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new Error("Missing Param: payee"));
    });
});
