import { CreateTransactionController } from "@/presentation/controllers";
import { badRequest, notFound } from "@/presentation/helpers";
import {
    EntityNotFoundException,
    InsufficientBalanceException,
    MissingParamException,
} from "@/presentation/exceptions";
import {
    CheckBalanceSpy,
    FindPayerPayeeByIdSpy,
    ValidationSpy,
} from "@/tests/presentation/mocks";

const makeSut = () => {
    const validationStub = new ValidationSpy();
    const checkBalanceStub = new CheckBalanceSpy();
    const findPayerPayeeByIdStub = new FindPayerPayeeByIdSpy();

    const sut = new CreateTransactionController(
        validationStub,
        checkBalanceStub,
        findPayerPayeeByIdStub
    );

    return { sut, validationStub, checkBalanceStub, findPayerPayeeByIdStub };
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
        expect(response).toEqual(badRequest(validationStub.error));
    });

    it("should returns 400 if no payer is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payee: 1,
            value: 100.0,
        };

        validationStub.error = new MissingParamException("payer");

        const response = await sut.handle(httpRequest);
        expect(response).toEqual(badRequest(validationStub.error));
    });

    it("should returns 400 if no payee is provided", async () => {
        const { sut, validationStub } = makeSut();

        const httpRequest: any = {
            payer: 1,
            value: 100.0,
        };

        validationStub.error = new MissingParamException("payee");

        const response = await sut.handle(httpRequest);
        expect(response).toEqual(badRequest(validationStub.error));
    });

    it("should returns 400 if balance is insufficient", async () => {
        const { sut, checkBalanceStub } = makeSut();

        const httpRequest: any = {
            payer: 1,
            payee: 2,
            value: 100.0,
        };

        checkBalanceStub.result = false;

        const response = await sut.handle(httpRequest);
        expect(response).toEqual(
            badRequest(new InsufficientBalanceException())
        );
    });

    it("should returns 404 if no payee if found", async () => {
        const { sut, findPayerPayeeByIdStub } = makeSut();

        const httpRequest: any = {
            payer: -1,
            payee: 2,
            value: 100.0,
        };

        jest.spyOn(
            findPayerPayeeByIdStub,
            "findPayerPayeeById"
        ).mockReturnValueOnce(Promise.resolve(null));

        const response = await sut.handle(httpRequest);
        expect(response).toEqual(notFound(new EntityNotFoundException("User")));
    });

    it("should returns 404 if no payer if found", async () => {
        const { sut, findPayerPayeeByIdStub } = makeSut();

        const httpRequest: any = {
            payer: 1,
            payee: -1,
            value: 100.0,
        };

        jest.spyOn(
            findPayerPayeeByIdStub,
            "findPayerPayeeById"
        ).mockReturnValueOnce(Promise.resolve(null));

        const response = await sut.handle(httpRequest);
        expect(response).toEqual(notFound(new EntityNotFoundException("User")));
    });
});
