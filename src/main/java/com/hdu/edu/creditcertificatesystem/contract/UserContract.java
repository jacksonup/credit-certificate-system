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
import org.web3j.tuples.generated.Tuple6;
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
/**
 * @author 阵雨
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class UserContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b50611945806100296000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063658d90f411610066578063658d90f41461013757806394c33b7d14610153578063b10c3cc91461016f578063be0a67801461019f578063ee209309146101cf57610093565b80633848149e14610098578063465c4105146100b65780634709161a146100e65780635aa9033c1461011b575b600080fd5b6100a06101ff565b6040516100ad91906111e5565b60405180910390f35b6100d060048036038101906100cb9190611350565b61030f565b6040516100dd91906113e3565b60405180910390f35b61010060048036038101906100fb91906113fe565b6103ec565b604051610112969594939291906114a0565b60405180910390f35b61013560048036038101906101309190611663565b61065e565b005b610151600480360381019061014c91906116ac565b610835565b005b61016d60048036038101906101689190611663565b61090f565b005b61018960048036038101906101849190611663565b610b3a565b60405161019691906111e5565b60405180910390f35b6101b960048036038101906101b491906116ac565b610e0d565b6040516101c691906116d9565b60405180910390f35b6101e960048036038101906101e491906113fe565b610eb9565b6040516101f691906113e3565b60405180910390f35b610207610eef565b6040518060c001604052806040518060400160405280600381526020017f686868000000000000000000000000000000000000000000000000000000000081525081526020016040518060400160405280600481526020017f3133323300000000000000000000000000000000000000000000000000000000815250815260200160008152602001606f81526020016040518060400160405280600581526020017f317133323300000000000000000000000000000000000000000000000000000081525081526020016040518060400160405280600581526020017f656d61696c000000000000000000000000000000000000000000000000000000815250815250905090565b6000808390506000839050805182511461032e576000925050506103e6565b60005b82518110156103de5781818151811061034d5761034c6116fb565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191683828151811061038d5761038c6116fb565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146103cb57600093505050506103e6565b80806103d690611759565b915050610331565b506001925050505b92915050565b600081805160208101820180518482526020830160208501208183528095505050505050600091509050806000018054610425906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610451906117d1565b801561049e5780601f106104735761010080835404028352916020019161049e565b820191906000526020600020905b81548152906001019060200180831161048157829003601f168201915b5050505050908060010180546104b3906117d1565b80601f01602080910402602001604051908101604052809291908181526020018280546104df906117d1565b801561052c5780601f106105015761010080835404028352916020019161052c565b820191906000526020600020905b81548152906001019060200180831161050f57829003601f168201915b50505050509080600201549080600301549080600401805461054d906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610579906117d1565b80156105c65780601f1061059b576101008083540402835291602001916105c6565b820191906000526020600020905b8154815290600101906020018083116105a957829003601f168201915b5050505050908060050180546105db906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610607906117d1565b80156106545780601f1061062957610100808354040283529160200191610654565b820191906000526020600020905b81548152906001019060200180831161063757829003601f168201915b5050505050905086565b60018160000151604051610672919061183f565b908152602001604051809103902060009054906101000a900460ff16156107f957600080600183600001516040516106aa919061183f565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b6002805490508110156107b2576107958260000151600283815481106106fd576106fc6116fb565b5b906000526020600020018054610712906117d1565b80601f016020809104026020016040519081016040528092919081815260200182805461073e906117d1565b801561078b5780601f106107605761010080835404028352916020019161078b565b820191906000526020600020905b81548152906001019060200180831161076e57829003601f168201915b505050505061030f565b1561079f576107b2565b80806107aa90611759565b9150506106d4565b6107bb81610835565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960016040516107eb91906113e3565b60405180910390a150610832565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a89600060405161082991906113e3565b60405180910390a15b50565b6000600280549050905080821061084c575061090c565b60008290505b60018261085f9190611856565b8110156108d9576002600182610875919061188a565b81548110610886576108856116fb565b5b90600052602060002001600282815481106108a4576108a36116fb565b5b906000526020600020019080546108ba906117d1565b6108c5929190610f25565b5080806108d190611759565b915050610852565b5060028054806108ec576108eb6118e0565b5b6001900381819060005260206000200160006109089190610fb2565b9055505b50565b60018160000151604051610923919061183f565b908152602001604051809103902060009054906101000a900460ff16610a88578060008260000151604051610958919061183f565b90815260200160405180910390206000820151816000019080519060200190610982929190610ff2565b50602082015181600101908051906020019061099f929190610ff2565b50604082015181600201556060820151816003015560808201518160040190805190602001906109d0929190610ff2565b5060a08201518160050190805190602001906109ed929190610ff2565b509050506001808260000151604051610a06919061183f565b908152602001604051809103902060006101000a81548160ff02191690831515021790555060028160000151908060018154018082558091505060019003906000526020600020016000909190919091509080519060200190610a6a929190610ff2565b5060036000815480929190610a7e90611759565b9190505550610b37565b8060008260000151604051610a9d919061183f565b90815260200160405180910390206000820151816000019080519060200190610ac7929190610ff2565b506020820151816001019080519060200190610ae4929190610ff2565b5060408201518160020155606082015181600301556080820151816004019080519060200190610b15929190610ff2565b5060a0820151816005019080519060200190610b32929190610ff2565b509050505b50565b610b42610eef565b60018260000151604051610b56919061183f565b908152602001604051809103902060009054906101000a900460ff1615610e075760008260000151604051610b8b919061183f565b90815260200160405180910390206040518060c0016040529081600082018054610bb4906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610be0906117d1565b8015610c2d5780601f10610c0257610100808354040283529160200191610c2d565b820191906000526020600020905b815481529060010190602001808311610c1057829003601f168201915b50505050508152602001600182018054610c46906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610c72906117d1565b8015610cbf5780601f10610c9457610100808354040283529160200191610cbf565b820191906000526020600020905b815481529060010190602001808311610ca257829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482018054610cec906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610d18906117d1565b8015610d655780601f10610d3a57610100808354040283529160200191610d65565b820191906000526020600020905b815481529060010190602001808311610d4857829003601f168201915b50505050508152602001600582018054610d7e906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610daa906117d1565b8015610df75780601f10610dcc57610100808354040283529160200191610df7565b820191906000526020600020905b815481529060010190602001808311610dda57829003601f168201915b5050505050815250509050610e08565b5b919050565b60028181548110610e1d57600080fd5b906000526020600020016000915090508054610e38906117d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610e64906117d1565b8015610eb15780601f10610e8657610100808354040283529160200191610eb1565b820191906000526020600020905b815481529060010190602001808311610e9457829003601f168201915b505050505081565b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b6040518060c001604052806060815260200160608152602001600081526020016000815260200160608152602001606081525090565b828054610f31906117d1565b90600052602060002090601f016020900481019282610f535760008555610fa1565b82601f10610f645780548555610fa1565b82800160010185558215610fa157600052602060002091601f016020900482015b82811115610fa0578254825591600101919060010190610f85565b5b509050610fae9190611078565b5090565b508054610fbe906117d1565b6000825580601f10610fd05750610fef565b601f016020900490600052602060002090810190610fee9190611078565b5b50565b828054610ffe906117d1565b90600052602060002090601f0160209004810192826110205760008555611067565b82601f1061103957805160ff1916838001178555611067565b82800160010185558215611067579182015b8281111561106657825182559160200191906001019061104b565b5b5090506110749190611078565b5090565b5b80821115611091576000816000905550600101611079565b5090565b600081519050919050565b600082825260208201905092915050565b60005b838110156110cf5780820151818401526020810190506110b4565b838111156110de576000848401525b50505050565b6000601f19601f8301169050919050565b600061110082611095565b61110a81856110a0565b935061111a8185602086016110b1565b611123816110e4565b840191505092915050565b6000819050919050565b6111418161112e565b82525050565b600060c083016000830151848203600086015261116482826110f5565b9150506020830151848203602086015261117e82826110f5565b91505060408301516111936040860182611138565b5060608301516111a66060860182611138565b50608083015184820360808601526111be82826110f5565b91505060a083015184820360a08601526111d882826110f5565b9150508091505092915050565b600060208201905081810360008301526111ff8184611147565b905092915050565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61125d826110e4565b810181811067ffffffffffffffff8211171561127c5761127b611225565b5b80604052505050565b600061128f611207565b905061129b8282611254565b919050565b600067ffffffffffffffff8211156112bb576112ba611225565b5b6112c4826110e4565b9050602081019050919050565b82818337600083830152505050565b60006112f36112ee846112a0565b611285565b90508281526020810184848401111561130f5761130e611220565b5b61131a8482856112d1565b509392505050565b600082601f8301126113375761133661121b565b5b81356113478482602086016112e0565b91505092915050565b6000806040838503121561136757611366611211565b5b600083013567ffffffffffffffff81111561138557611384611216565b5b61139185828601611322565b925050602083013567ffffffffffffffff8111156113b2576113b1611216565b5b6113be85828601611322565b9150509250929050565b60008115159050919050565b6113dd816113c8565b82525050565b60006020820190506113f860008301846113d4565b92915050565b60006020828403121561141457611413611211565b5b600082013567ffffffffffffffff81111561143257611431611216565b5b61143e84828501611322565b91505092915050565b600082825260208201905092915050565b600061146382611095565b61146d8185611447565b935061147d8185602086016110b1565b611486816110e4565b840191505092915050565b61149a8161112e565b82525050565b600060c08201905081810360008301526114ba8189611458565b905081810360208301526114ce8188611458565b90506114dd6040830187611491565b6114ea6060830186611491565b81810360808301526114fc8185611458565b905081810360a08301526115108184611458565b9050979650505050505050565b600080fd5b600080fd5b6115308161112e565b811461153b57600080fd5b50565b60008135905061154d81611527565b92915050565b600060c082840312156115695761156861151d565b5b61157360c0611285565b9050600082013567ffffffffffffffff81111561159357611592611522565b5b61159f84828501611322565b600083015250602082013567ffffffffffffffff8111156115c3576115c2611522565b5b6115cf84828501611322565b60208301525060406115e38482850161153e565b60408301525060606115f78482850161153e565b606083015250608082013567ffffffffffffffff81111561161b5761161a611522565b5b61162784828501611322565b60808301525060a082013567ffffffffffffffff81111561164b5761164a611522565b5b61165784828501611322565b60a08301525092915050565b60006020828403121561167957611678611211565b5b600082013567ffffffffffffffff81111561169757611696611216565b5b6116a384828501611553565b91505092915050565b6000602082840312156116c2576116c1611211565b5b60006116d08482850161153e565b91505092915050565b600060208201905081810360008301526116f38184611458565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006117648261112e565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156117975761179661172a565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806117e957607f821691505b602082108114156117fd576117fc6117a2565b5b50919050565b600081905092915050565b600061181982611095565b6118238185611803565b93506118338185602086016110b1565b80840191505092915050565b600061184b828461180e565b915081905092915050565b60006118618261112e565b915061186c8361112e565b92508282101561187f5761187e61172a565b5b828203905092915050565b60006118958261112e565b91506118a08361112e565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff038211156118d5576118d461172a565b5b828201905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea2646970667358221220a659123a601288b4576b313e964431ad81b4bb7c014af803c5a4f2c9b79aa4d764736f6c634300080a0033";

    public static final String FUNC_DELETEUSER = "deleteUser";

    public static final String FUNC_GETAUSEINFO = "getAUseInfo";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVEUSERINFOKEYATINDEX = "removeUserInfoKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_USERINFOINSERTED = "userInfoInserted";

    public static final String FUNC_USERINFOKEY = "userInfoKey";

    public static final String FUNC_USERINFOS = "userInfos";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected UserContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> deleteUser(UserInfo _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEUSER,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<UserInfo> getAUseInfo() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAUSEINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<UserInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, UserInfo.class);
    }

    public RemoteFunctionCall<UserInfo> getEntity(UserInfo _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_user),
                Arrays.<TypeReference<?>>asList(new TypeReference<UserInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, UserInfo.class);
    }

    public RemoteFunctionCall<Boolean> isEqual(String a, String b) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISEQUAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a),
                        new org.web3j.abi.datatypes.Utf8String(b)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeUserInfoKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEUSERINFOKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(UserInfo _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> userInfoInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_USERINFOINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> userInfoKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_USERINFOKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple6<String, String, BigInteger, BigInteger, String, String>> userInfos(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_USERINFOS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple6<String, String, BigInteger, BigInteger, String, String>>(function,
                new Callable<Tuple6<String, String, BigInteger, BigInteger, String, String>>() {
                    @Override
                    public Tuple6<String, String, BigInteger, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, String, BigInteger, BigInteger, String, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue());
                    }
                });
    }

    @Deprecated
    public static UserContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class UserInfo extends DynamicStruct {
        public String account;

        public String password;

        public BigInteger role;

        public BigInteger createTime;

        public String phone;

        public String email;

        public UserInfo(String account, String password, BigInteger role, BigInteger createTime, String phone, String email) {
            super(new org.web3j.abi.datatypes.Utf8String(account),new org.web3j.abi.datatypes.Utf8String(password),new org.web3j.abi.datatypes.generated.Uint256(role),new org.web3j.abi.datatypes.generated.Uint256(createTime),new org.web3j.abi.datatypes.Utf8String(phone),new org.web3j.abi.datatypes.Utf8String(email));
            this.account = account;
            this.password = password;
            this.role = role;
            this.createTime = createTime;
            this.phone = phone;
            this.email = email;
        }

        public UserInfo(Utf8String account, Utf8String password, Uint256 role, Uint256 createTime, Utf8String phone, Utf8String email) {
            super(account,password,role,createTime,phone,email);
            this.account = account.getValue();
            this.password = password.getValue();
            this.role = role.getValue();
            this.createTime = createTime.getValue();
            this.phone = phone.getValue();
            this.email = email.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}
