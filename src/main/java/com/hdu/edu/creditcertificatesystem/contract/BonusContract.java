package com.hdu.edu.creditcertificatesystem.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class BonusContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b5061209d806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806359583c2a1161007157806359583c2a1461019057806362d5d2f6146101ac57806382a12f3a146101c85780639459e1ed146101f85780639e6ae7a514610228578063d40324fb14610244576100a9565b80631fa6147d146100ae5780633e12f3c1146100de578063465c410514610112578063485c79471461014257806353ed514314610172575b600080fd5b6100c860048036038101906100c39190611655565b610260565b6040516100d591906116b9565b60405180910390f35b6100f860048036038101906100f39190611655565b610296565b604051610109959493929190611775565b60405180910390f35b61012c600480360381019061012791906117d6565b6103f2565b60405161013991906116b9565b60405180910390f35b61015c6004803603810190610157919061187a565b6104cf565b6040516101699190611a52565b60405180910390f35b61017a610818565b6040516101879190611a52565b60405180910390f35b6101aa60048036038101906101a59190611b5a565b610a42565b005b6101c660048036038101906101c19190611c71565b610c7b565b005b6101e260048036038101906101dd9190611c71565b610e52565b6040516101ef9190611d37565b60405180910390f35b610212600480360381019061020d9190611d59565b61100b565b60405161021f9190611d86565b60405180910390f35b610242600480360381019061023d9190611d59565b6110b7565b005b61025e60048036038101906102599190611c71565b611191565b005b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b6000818051602081018201805184825260208301602085012081835280955050505050506000915090508060000180546102cf90611dd7565b80601f01602080910402602001604051908101604052809291908181526020018280546102fb90611dd7565b80156103485780601f1061031d57610100808354040283529160200191610348565b820191906000526020600020905b81548152906001019060200180831161032b57829003601f168201915b50505050509080600101805461035d90611dd7565b80601f016020809104026020016040519081016040528092919081815260200182805461038990611dd7565b80156103d65780601f106103ab576101008083540402835291602001916103d6565b820191906000526020600020905b8154815290600101906020018083116103b957829003601f168201915b5050505050908060020154908060030154908060040154905085565b60008083905060008390508051825114610411576000925050506104c9565b60005b82518110156104c1578181815181106104305761042f611e09565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168382815181106104705761046f611e09565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146104ae57600093505050506104c9565b80806104b990611e67565b915050610414565b506001925050505b92915050565b6060600060028054905067ffffffffffffffff8111156104f2576104f161152a565b5b60405190808252806020026020018201604052801561052b57816020015b61051861135c565b8152602001906001900390816105105790505b5090506000805b6002805490508110156107045760006002828154811061055557610554611e09565b5b9060005260206000200160405161056c9190611f4f565b90815260200160405180910390206040518060a001604052908160008201805461059590611dd7565b80601f01602080910402602001604051908101604052809291908181526020018280546105c190611dd7565b801561060e5780601f106105e35761010080835404028352916020019161060e565b820191906000526020600020905b8154815290600101906020018083116105f157829003601f168201915b5050505050815260200160018201805461062790611dd7565b80601f016020809104026020016040519081016040528092919081815260200182805461065390611dd7565b80156106a05780601f10610675576101008083540402835291602001916106a0565b820191906000526020600020905b81548152906001019060200180831161068357829003601f168201915b5050505050815260200160028201548152602001600382015481526020016004820154815250508383806106d390611e67565b9450815181106106e6576106e5611e09565b5b602002602001018190525080806106fc90611e67565b915050610532565b5084841015610714575050610812565b6001816107219190611f66565b841115610738576001816107359190611f66565b93505b600185856107469190611f66565b6107509190611f9a565b67ffffffffffffffff8111156107695761076861152a565b5b6040519080825280602002602001820160405280156107a257816020015b61078f61135c565b8152602001906001900390816107875790505b5092506000905060008590505b84811161080e578281815181106107c9576107c8611e09565b5b60200260200101518483806107dd90611e67565b9450815181106107f0576107ef611e09565b5b6020026020010181905250808061080690611e67565b9150506107af565b5050505b92915050565b606060028054905067ffffffffffffffff8111156108395761083861152a565b5b60405190808252806020026020018201604052801561087257816020015b61085f61135c565b8152602001906001900390816108575790505b50905060005b600280549050811015610a3e5760006002828154811061089b5761089a611e09565b5b906000526020600020016040516108b29190611f4f565b90815260200160405180910390206040518060a00160405290816000820180546108db90611dd7565b80601f016020809104026020016040519081016040528092919081815260200182805461090790611dd7565b80156109545780601f1061092957610100808354040283529160200191610954565b820191906000526020600020905b81548152906001019060200180831161093757829003601f168201915b5050505050815260200160018201805461096d90611dd7565b80601f016020809104026020016040519081016040528092919081815260200182805461099990611dd7565b80156109e65780601f106109bb576101008083540402835291602001916109e6565b820191906000526020600020905b8154815290600101906020018083116109c957829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482015481525050828281518110610a2057610a1f611e09565b5b60200260200101819052508080610a3690611e67565b915050610878565b5090565b60005b8151811015610c77576001828281518110610a6357610a62611e09565b5b6020026020010151604051610a789190612021565b908152602001604051809103902060009054906101000a900460ff1615610c2b576000806001848481518110610ab157610ab0611e09565b5b6020026020010151604051610ac69190612021565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610be457610bc7838381518110610b1357610b12611e09565b5b602002602001015160028381548110610b2f57610b2e611e09565b5b906000526020600020018054610b4490611dd7565b80601f0160208091040260200160405190810160405280929190818152602001828054610b7090611dd7565b8015610bbd5780601f10610b9257610100808354040283529160200191610bbd565b820191906000526020600020905b815481529060010190602001808311610ba057829003601f168201915b50505050506103f2565b15610bd157610be4565b8080610bdc90611e67565b915050610af0565b610bed816110b7565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610c1d91906116b9565b60405180910390a150610c64565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896000604051610c5b91906116b9565b60405180910390a15b8080610c6f90611e67565b915050610a45565b5050565b60018160000151604051610c8f9190612021565b908152602001604051809103902060009054906101000a900460ff1615610e165760008060018360000151604051610cc79190612021565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610dcf57610db2826000015160028381548110610d1a57610d19611e09565b5b906000526020600020018054610d2f90611dd7565b80601f0160208091040260200160405190810160405280929190818152602001828054610d5b90611dd7565b8015610da85780601f10610d7d57610100808354040283529160200191610da8565b820191906000526020600020905b815481529060010190602001808311610d8b57829003601f168201915b50505050506103f2565b15610dbc57610dcf565b8080610dc790611e67565b915050610cf1565b610dd8816110b7565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610e0891906116b9565b60405180910390a150610e4f565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896000604051610e4691906116b9565b60405180910390a15b50565b610e5a61135c565b60018260000151604051610e6e9190612021565b908152602001604051809103902060009054906101000a900460ff16156110055760008260000151604051610ea39190612021565b90815260200160405180910390206040518060a0016040529081600082018054610ecc90611dd7565b80601f0160208091040260200160405190810160405280929190818152602001828054610ef890611dd7565b8015610f455780601f10610f1a57610100808354040283529160200191610f45565b820191906000526020600020905b815481529060010190602001808311610f2857829003601f168201915b50505050508152602001600182018054610f5e90611dd7565b80601f0160208091040260200160405190810160405280929190818152602001828054610f8a90611dd7565b8015610fd75780601f10610fac57610100808354040283529160200191610fd7565b820191906000526020600020905b815481529060010190602001808311610fba57829003601f168201915b5050505050815260200160028201548152602001600382015481526020016004820154815250509050611006565b5b919050565b6002818154811061101b57600080fd5b90600052602060002001600091509050805461103690611dd7565b80601f016020809104026020016040519081016040528092919081815260200182805461106290611dd7565b80156110af5780601f10611084576101008083540402835291602001916110af565b820191906000526020600020905b81548152906001019060200180831161109257829003601f168201915b505050505081565b600060028054905090508082106110ce575061118e565b60008290505b6001826110e19190611f66565b81101561115b5760026001826110f79190611f9a565b8154811061110857611107611e09565b5b906000526020600020016002828154811061112657611125611e09565b5b9060005260206000200190805461113c90611dd7565b61114792919061138b565b50808061115390611e67565b9150506110d4565b50600280548061116e5761116d612038565b5b60019003818190600052602060002001600061118a9190611418565b9055505b50565b600181600001516040516111a59190612021565b908152602001604051809103902060009054906101000a900460ff166112da5780600082600001516040516111da9190612021565b90815260200160405180910390206000820151816000019080519060200190611204929190611458565b506020820151816001019080519060200190611221929190611458565b5060408201518160020155606082015181600301556080820151816004015590505060018082600001516040516112589190612021565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600281600001519080600181540180825580915050600190039060005260206000200160009091909190915090805190602001906112bc929190611458565b50600360008154809291906112d090611e67565b9190505550611359565b80600082600001516040516112ef9190612021565b90815260200160405180910390206000820151816000019080519060200190611319929190611458565b506020820151816001019080519060200190611336929190611458565b506040820151816002015560608201518160030155608082015181600401559050505b50565b6040518060a0016040528060608152602001606081526020016000815260200160008152602001600081525090565b82805461139790611dd7565b90600052602060002090601f0160209004810192826113b95760008555611407565b82601f106113ca5780548555611407565b8280016001018555821561140757600052602060002091601f016020900482015b828111156114065782548255916001019190600101906113eb565b5b50905061141491906114de565b5090565b50805461142490611dd7565b6000825580601f106114365750611455565b601f01602090049060005260206000209081019061145491906114de565b5b50565b82805461146490611dd7565b90600052602060002090601f01602090048101928261148657600085556114cd565b82601f1061149f57805160ff19168380011785556114cd565b828001600101855582156114cd579182015b828111156114cc5782518255916020019190600101906114b1565b5b5090506114da91906114de565b5090565b5b808211156114f75760008160009055506001016114df565b5090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61156282611519565b810181811067ffffffffffffffff821117156115815761158061152a565b5b80604052505050565b60006115946114fb565b90506115a08282611559565b919050565b600067ffffffffffffffff8211156115c0576115bf61152a565b5b6115c982611519565b9050602081019050919050565b82818337600083830152505050565b60006115f86115f3846115a5565b61158a565b90508281526020810184848401111561161457611613611514565b5b61161f8482856115d6565b509392505050565b600082601f83011261163c5761163b61150f565b5b813561164c8482602086016115e5565b91505092915050565b60006020828403121561166b5761166a611505565b5b600082013567ffffffffffffffff8111156116895761168861150a565b5b61169584828501611627565b91505092915050565b60008115159050919050565b6116b38161169e565b82525050565b60006020820190506116ce60008301846116aa565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561170e5780820151818401526020810190506116f3565b8381111561171d576000848401525b50505050565b600061172e826116d4565b61173881856116df565b93506117488185602086016116f0565b61175181611519565b840191505092915050565b6000819050919050565b61176f8161175c565b82525050565b600060a082019050818103600083015261178f8188611723565b905081810360208301526117a38187611723565b90506117b26040830186611766565b6117bf6060830185611766565b6117cc6080830184611766565b9695505050505050565b600080604083850312156117ed576117ec611505565b5b600083013567ffffffffffffffff81111561180b5761180a61150a565b5b61181785828601611627565b925050602083013567ffffffffffffffff8111156118385761183761150a565b5b61184485828601611627565b9150509250929050565b6118578161175c565b811461186257600080fd5b50565b6000813590506118748161184e565b92915050565b6000806040838503121561189157611890611505565b5b600061189f85828601611865565b92505060206118b085828601611865565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600082825260208201905092915050565b6000611902826116d4565b61190c81856118e6565b935061191c8185602086016116f0565b61192581611519565b840191505092915050565b6119398161175c565b82525050565b600060a083016000830151848203600086015261195c82826118f7565b9150506020830151848203602086015261197682826118f7565b915050604083015161198b6040860182611930565b50606083015161199e6060860182611930565b5060808301516119b16080860182611930565b508091505092915050565b60006119c8838361193f565b905092915050565b6000602082019050919050565b60006119e8826118ba565b6119f281856118c5565b935083602082028501611a04856118d6565b8060005b85811015611a405784840389528151611a2185826119bc565b9450611a2c836119d0565b925060208a01995050600181019050611a08565b50829750879550505050505092915050565b60006020820190508181036000830152611a6c81846119dd565b905092915050565b600067ffffffffffffffff821115611a8f57611a8e61152a565b5b602082029050602081019050919050565b600080fd5b6000611ab8611ab384611a74565b61158a565b90508083825260208201905060208402830185811115611adb57611ada611aa0565b5b835b81811015611b2257803567ffffffffffffffff811115611b0057611aff61150f565b5b808601611b0d8982611627565b85526020850194505050602081019050611add565b5050509392505050565b600082601f830112611b4157611b4061150f565b5b8135611b51848260208601611aa5565b91505092915050565b600060208284031215611b7057611b6f611505565b5b600082013567ffffffffffffffff811115611b8e57611b8d61150a565b5b611b9a84828501611b2c565b91505092915050565b600080fd5b600080fd5b600060a08284031215611bc357611bc2611ba3565b5b611bcd60a061158a565b9050600082013567ffffffffffffffff811115611bed57611bec611ba8565b5b611bf984828501611627565b600083015250602082013567ffffffffffffffff811115611c1d57611c1c611ba8565b5b611c2984828501611627565b6020830152506040611c3d84828501611865565b6040830152506060611c5184828501611865565b6060830152506080611c6584828501611865565b60808301525092915050565b600060208284031215611c8757611c86611505565b5b600082013567ffffffffffffffff811115611ca557611ca461150a565b5b611cb184828501611bad565b91505092915050565b600060a0830160008301518482036000860152611cd782826118f7565b91505060208301518482036020860152611cf182826118f7565b9150506040830151611d066040860182611930565b506060830151611d196060860182611930565b506080830151611d2c6080860182611930565b508091505092915050565b60006020820190508181036000830152611d518184611cba565b905092915050565b600060208284031215611d6f57611d6e611505565b5b6000611d7d84828501611865565b91505092915050565b60006020820190508181036000830152611da08184611723565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680611def57607f821691505b60208210811415611e0357611e02611da8565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611e728261175c565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415611ea557611ea4611e38565b5b600182019050919050565b600081905092915050565b60008190508160005260206000209050919050565b60008154611edd81611dd7565b611ee78186611eb0565b94506001821660008114611f025760018114611f1357611f46565b60ff19831686528186019350611f46565b611f1c85611ebb565b60005b83811015611f3e57815481890152600182019150602081019050611f1f565b838801955050505b50505092915050565b6000611f5b8284611ed0565b915081905092915050565b6000611f718261175c565b9150611f7c8361175c565b925082821015611f8f57611f8e611e38565b5b828203905092915050565b6000611fa58261175c565b9150611fb08361175c565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611fe557611fe4611e38565b5b828201905092915050565b6000611ffb826116d4565b6120058185611eb0565b93506120158185602086016116f0565b80840191505092915050565b600061202d8284611ff0565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea26469706673582212209067ee891ce86e88f04a4b1ae2d32f0c1dbe8fa350dae4457d81fcaff1d4905064736f6c634300080a0033";

    public static final String FUNC_BATCHDELETE = "batchDelete";

    public static final String FUNC_BONUSINSERTED = "bonusInserted";

    public static final String FUNC_BONUSKEY = "bonusKey";

    public static final String FUNC_BONUSES = "bonuses";

    public static final String FUNC_DELETEUSER = "deleteUser";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_GETLISTPAGE = "getListPage";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVEBONUSKEYATINDEX = "removeBonusKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected BonusContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BonusContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BonusContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BonusContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DeleteMsgEventResponse> getDeleteMsgEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DELETEMSG_EVENT, transactionReceipt);
        ArrayList<DeleteMsgEventResponse> responses = new ArrayList<DeleteMsgEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeleteMsgEventResponse typedResponse = new DeleteMsgEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.code = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DeleteMsgEventResponse> deleteMsgEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DeleteMsgEventResponse>() {
            @Override
            public DeleteMsgEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DELETEMSG_EVENT, log);
                DeleteMsgEventResponse typedResponse = new DeleteMsgEventResponse();
                typedResponse.log = log;
                typedResponse.code = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DeleteMsgEventResponse> deleteMsgEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELETEMSG_EVENT));
        return deleteMsgEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> batchDelete(List<String> idList) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BATCHDELETE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(idList, org.web3j.abi.datatypes.Utf8String.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> bonusInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BONUSINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> bonusKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BONUSKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>> bonuses(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BONUSES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> deleteUser(Bonus _bonus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEUSER,
                Arrays.<Type>asList(_bonus),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bonus>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Bonus> getEntity(Bonus _bonus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_bonus),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bonus>() {}));
        return executeRemoteCallSingleValueReturn(function, Bonus.class);
    }

    public RemoteFunctionCall<List> getListPage(BigInteger begin, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTPAGE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(begin),
                        new org.web3j.abi.datatypes.generated.Uint256(end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bonus>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isEqual(String a, String b) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISEQUAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a),
                        new org.web3j.abi.datatypes.Utf8String(b)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeBonusKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEBONUSKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(Bonus _bonus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_bonus),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static BonusContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BonusContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BonusContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BonusContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BonusContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BonusContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BonusContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BonusContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BonusContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BonusContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BonusContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BonusContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BonusContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BonusContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BonusContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BonusContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Bonus extends DynamicStruct {
        public String id;

        public String bonusName;

        public BigInteger acquireTime;

        public BigInteger startTime;

        public BigInteger proofPic;

        public Bonus(String id, String bonusName, BigInteger acquireTime, BigInteger startTime, BigInteger proofPic) {
            super(new org.web3j.abi.datatypes.Utf8String(id),new org.web3j.abi.datatypes.Utf8String(bonusName),new org.web3j.abi.datatypes.generated.Uint256(acquireTime),new org.web3j.abi.datatypes.generated.Uint256(startTime),new org.web3j.abi.datatypes.generated.Uint256(proofPic));
            this.id = id;
            this.bonusName = bonusName;
            this.acquireTime = acquireTime;
            this.startTime = startTime;
            this.proofPic = proofPic;
        }

        public Bonus(Utf8String id, Utf8String bonusName, Uint256 acquireTime, Uint256 startTime, Uint256 proofPic) {
            super(id,bonusName,acquireTime,startTime,proofPic);
            this.id = id.getValue();
            this.bonusName = bonusName.getValue();
            this.acquireTime = acquireTime.getValue();
            this.startTime = startTime.getValue();
            this.proofPic = proofPic.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}
