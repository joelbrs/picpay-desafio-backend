import { CreateTransactionController } from "@/presentation/controllers";
import { MissingParamException } from "@/presentation/exceptions";
import { ValidationSpy } from "@/tests/presentation/mocks";

const makeSut = () => {
    const validationStub = new ValidationSpy();
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

        validationStub.error = new MissingParamException("value");

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new MissingParamException("value"));
    });

    it("should returns 400 if no payer is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payee: 1,
            value: 100.0,
        };

        validationStub.error = new MissingParamException("payer");

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new MissingParamException("payer"));
    });

    it("should returns 400 if no payee is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payer: 1,
            value: 100.0,
        };

        validationStub.error = new MissingParamException("payee");

        const response = await sut.handle(httpRequest);
        expect(response.statusCode).toBe(400);
        expect(response.body).toEqual(new MissingParamException("payee"));
    });
});
