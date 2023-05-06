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
import org.web3j.tuples.generated.Tuple4;
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
public class SpeechContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b50612015806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c8063a19e748d11610071578063a19e748d14610164578063a72ce92414610194578063a9396676146101b0578063f3697f32146101e0578063fbab827a146101fc578063fd9846a51461022c576100a9565b8063465c4105146100ae578063485c7947146100de57806353ed51431461010e57806355df34da1461012c57806359583c2a14610148575b600080fd5b6100c860048036038101906100c39190611615565b61025f565b6040516100d591906116a8565b60405180910390f35b6100f860048036038101906100f391906116f9565b61033c565b60405161010591906118fc565b60405180910390f35b61011661067b565b60405161012391906118fc565b60405180910390f35b610146600480360381019061014191906119d8565b61089b565b005b610162600480360381019061015d9190611b07565b610a52565b005b61017e600480360381019061017991906119d8565b610c8b565b60405161018b9190611bba565b60405180910390f35b6101ae60048036038101906101a991906119d8565b610e3a565b005b6101ca60048036038101906101c59190611bdc565b611011565b6040516101d791906116a8565b60405180910390f35b6101fa60048036038101906101f59190611c25565b611047565b005b61021660048036038101906102119190611c25565b611121565b6040516102239190611c9c565b60405180910390f35b61024660048036038101906102419190611bdc565b6111cd565b6040516102569493929190611ccd565b60405180910390f35b6000808390506000839050805182511461027e57600092505050610336565b60005b825181101561032e5781818151811061029d5761029c611d20565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168382815181106102dd576102dc611d20565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161461031b5760009350505050610336565b808061032690611d7e565b915050610281565b506001925050505b92915050565b6060600060028054905067ffffffffffffffff81111561035f5761035e6114ea565b5b60405190808252806020026020018201604052801561039857816020015b610385611323565b81526020019060019003908161037d5790505b5090506000805b600280549050811015610567576000600282815481106103c2576103c1611d20565b5b906000526020600020016040516103d99190611ec7565b908152602001604051809103902060405180608001604052908160008201805461040290611df6565b80601f016020809104026020016040519081016040528092919081815260200182805461042e90611df6565b801561047b5780601f106104505761010080835404028352916020019161047b565b820191906000526020600020905b81548152906001019060200180831161045e57829003601f168201915b5050505050815260200160018201805461049490611df6565b80601f01602080910402602001604051908101604052809291908181526020018280546104c090611df6565b801561050d5780601f106104e25761010080835404028352916020019161050d565b820191906000526020600020905b8154815290600101906020018083116104f057829003601f168201915b505050505081526020016002820154815260200160038201548152505083838061053690611d7e565b94508151811061054957610548611d20565b5b6020026020010181905250808061055f90611d7e565b91505061039f565b5084841015610577575050610675565b6001816105849190611ede565b84111561059b576001816105989190611ede565b93505b600185856105a99190611ede565b6105b39190611f12565b67ffffffffffffffff8111156105cc576105cb6114ea565b5b60405190808252806020026020018201604052801561060557816020015b6105f2611323565b8152602001906001900390816105ea5790505b5092506000905060008590505b8481116106715782818151811061062c5761062b611d20565b5b602002602001015184838061064090611d7e565b94508151811061065357610652611d20565b5b6020026020010181905250808061066990611d7e565b915050610612565b5050505b92915050565b606060028054905067ffffffffffffffff81111561069c5761069b6114ea565b5b6040519080825280602002602001820160405280156106d557816020015b6106c2611323565b8152602001906001900390816106ba5790505b50905060005b600280549050811015610897576000600282815481106106fe576106fd611d20565b5b906000526020600020016040516107159190611ec7565b908152602001604051809103902060405180608001604052908160008201805461073e90611df6565b80601f016020809104026020016040519081016040528092919081815260200182805461076a90611df6565b80156107b75780601f1061078c576101008083540402835291602001916107b7565b820191906000526020600020905b81548152906001019060200180831161079a57829003601f168201915b505050505081526020016001820180546107d090611df6565b80601f01602080910402602001604051908101604052809291908181526020018280546107fc90611df6565b80156108495780601f1061081e57610100808354040283529160200191610849565b820191906000526020600020905b81548152906001019060200180831161082c57829003601f168201915b505050505081526020016002820154815260200160038201548152505082828151811061087957610878611d20565b5b6020026020010181905250808061088f90611d7e565b9150506106db565b5090565b600181600001516040516108af9190611f99565b908152602001604051809103902060009054906101000a900460ff166109da5780600082600001516040516108e49190611f99565b9081526020016040518091039020600082015181600001908051906020019061090e92919061134b565b50602082015181600101908051906020019061092b92919061134b565b50604082015181600201556060820151816003015590505060018082600001516040516109589190611f99565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600281600001519080600181540180825580915050600190039060005260206000200160009091909190915090805190602001906109bc92919061134b565b50600360008154809291906109d090611d7e565b9190505550610a4f565b80600082600001516040516109ef9190611f99565b90815260200160405180910390206000820151816000019080519060200190610a1992919061134b565b506020820151816001019080519060200190610a3692919061134b565b5060408201518160020155606082015181600301559050505b50565b60005b8151811015610c87576001828281518110610a7357610a72611d20565b5b6020026020010151604051610a889190611f99565b908152602001604051809103902060009054906101000a900460ff1615610c3b576000806001848481518110610ac157610ac0611d20565b5b6020026020010151604051610ad69190611f99565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610bf457610bd7838381518110610b2357610b22611d20565b5b602002602001015160028381548110610b3f57610b3e611d20565b5b906000526020600020018054610b5490611df6565b80601f0160208091040260200160405190810160405280929190818152602001828054610b8090611df6565b8015610bcd5780601f10610ba257610100808354040283529160200191610bcd565b820191906000526020600020905b815481529060010190602001808311610bb057829003601f168201915b505050505061025f565b15610be157610bf4565b8080610bec90611d7e565b915050610b00565b610bfd81611047565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610c2d91906116a8565b60405180910390a150610c74565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896000604051610c6b91906116a8565b60405180910390a15b8080610c7f90611d7e565b915050610a55565b5050565b610c93611323565b60018260000151604051610ca79190611f99565b908152602001604051809103902060009054906101000a900460ff1615610e345760008260000151604051610cdc9190611f99565b9081526020016040518091039020604051806080016040529081600082018054610d0590611df6565b80601f0160208091040260200160405190810160405280929190818152602001828054610d3190611df6565b8015610d7e5780601f10610d5357610100808354040283529160200191610d7e565b820191906000526020600020905b815481529060010190602001808311610d6157829003601f168201915b50505050508152602001600182018054610d9790611df6565b80601f0160208091040260200160405190810160405280929190818152602001828054610dc390611df6565b8015610e105780601f10610de557610100808354040283529160200191610e10565b820191906000526020600020905b815481529060010190602001808311610df357829003601f168201915b50505050508152602001600282015481526020016003820154815250509050610e35565b5b919050565b60018160000151604051610e4e9190611f99565b908152602001604051809103902060009054906101000a900460ff1615610fd55760008060018360000151604051610e869190611f99565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610f8e57610f71826000015160028381548110610ed957610ed8611d20565b5b906000526020600020018054610eee90611df6565b80601f0160208091040260200160405190810160405280929190818152602001828054610f1a90611df6565b8015610f675780601f10610f3c57610100808354040283529160200191610f67565b820191906000526020600020905b815481529060010190602001808311610f4a57829003601f168201915b505050505061025f565b15610f7b57610f8e565b8080610f8690611d7e565b915050610eb0565b610f9781611047565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610fc791906116a8565b60405180910390a15061100e565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a89600060405161100591906116a8565b60405180910390a15b50565b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b6000600280549050905080821061105e575061111e565b60008290505b6001826110719190611ede565b8110156110eb5760026001826110879190611f12565b8154811061109857611097611d20565b5b90600052602060002001600282815481106110b6576110b5611d20565b5b906000526020600020019080546110cc90611df6565b6110d79291906113d1565b5080806110e390611d7e565b915050611064565b5060028054806110fe576110fd611fb0565b5b60019003818190600052602060002001600061111a919061145e565b9055505b50565b6002818154811061113157600080fd5b90600052602060002001600091509050805461114c90611df6565b80601f016020809104026020016040519081016040528092919081815260200182805461117890611df6565b80156111c55780601f1061119a576101008083540402835291602001916111c5565b820191906000526020600020905b8154815290600101906020018083116111a857829003601f168201915b505050505081565b60008180516020810182018051848252602083016020850120818352809550505050505060009150905080600001805461120690611df6565b80601f016020809104026020016040519081016040528092919081815260200182805461123290611df6565b801561127f5780601f106112545761010080835404028352916020019161127f565b820191906000526020600020905b81548152906001019060200180831161126257829003601f168201915b50505050509080600101805461129490611df6565b80601f01602080910402602001604051908101604052809291908181526020018280546112c090611df6565b801561130d5780601f106112e25761010080835404028352916020019161130d565b820191906000526020600020905b8154815290600101906020018083116112f057829003601f168201915b5050505050908060020154908060030154905084565b6040518060800160405280606081526020016060815260200160008152602001600081525090565b82805461135790611df6565b90600052602060002090601f01602090048101928261137957600085556113c0565b82601f1061139257805160ff19168380011785556113c0565b828001600101855582156113c0579182015b828111156113bf5782518255916020019190600101906113a4565b5b5090506113cd919061149e565b5090565b8280546113dd90611df6565b90600052602060002090601f0160209004810192826113ff576000855561144d565b82601f10611410578054855561144d565b8280016001018555821561144d57600052602060002091601f016020900482015b8281111561144c578254825591600101919060010190611431565b5b50905061145a919061149e565b5090565b50805461146a90611df6565b6000825580601f1061147c575061149b565b601f01602090049060005260206000209081019061149a919061149e565b5b50565b5b808211156114b757600081600090555060010161149f565b5090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611522826114d9565b810181811067ffffffffffffffff82111715611541576115406114ea565b5b80604052505050565b60006115546114bb565b90506115608282611519565b919050565b600067ffffffffffffffff8211156115805761157f6114ea565b5b611589826114d9565b9050602081019050919050565b82818337600083830152505050565b60006115b86115b384611565565b61154a565b9050828152602081018484840111156115d4576115d36114d4565b5b6115df848285611596565b509392505050565b600082601f8301126115fc576115fb6114cf565b5b813561160c8482602086016115a5565b91505092915050565b6000806040838503121561162c5761162b6114c5565b5b600083013567ffffffffffffffff81111561164a576116496114ca565b5b611656858286016115e7565b925050602083013567ffffffffffffffff811115611677576116766114ca565b5b611683858286016115e7565b9150509250929050565b60008115159050919050565b6116a28161168d565b82525050565b60006020820190506116bd6000830184611699565b92915050565b6000819050919050565b6116d6816116c3565b81146116e157600080fd5b50565b6000813590506116f3816116cd565b92915050565b600080604083850312156117105761170f6114c5565b5b600061171e858286016116e4565b925050602061172f858286016116e4565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561179f578082015181840152602081019050611784565b838111156117ae576000848401525b50505050565b60006117bf82611765565b6117c98185611770565b93506117d9818560208601611781565b6117e2816114d9565b840191505092915050565b6117f6816116c3565b82525050565b6000608083016000830151848203600086015261181982826117b4565b9150506020830151848203602086015261183382826117b4565b915050604083015161184860408601826117ed565b50606083015161185b60608601826117ed565b508091505092915050565b600061187283836117fc565b905092915050565b6000602082019050919050565b600061189282611739565b61189c8185611744565b9350836020820285016118ae85611755565b8060005b858110156118ea57848403895281516118cb8582611866565b94506118d68361187a565b925060208a019950506001810190506118b2565b50829750879550505050505092915050565b600060208201905081810360008301526119168184611887565b905092915050565b600080fd5b600080fd5b60006080828403121561193e5761193d61191e565b5b611948608061154a565b9050600082013567ffffffffffffffff81111561196857611967611923565b5b611974848285016115e7565b600083015250602082013567ffffffffffffffff81111561199857611997611923565b5b6119a4848285016115e7565b60208301525060406119b8848285016116e4565b60408301525060606119cc848285016116e4565b60608301525092915050565b6000602082840312156119ee576119ed6114c5565b5b600082013567ffffffffffffffff811115611a0c57611a0b6114ca565b5b611a1884828501611928565b91505092915050565b600067ffffffffffffffff821115611a3c57611a3b6114ea565b5b602082029050602081019050919050565b600080fd5b6000611a65611a6084611a21565b61154a565b90508083825260208201905060208402830185811115611a8857611a87611a4d565b5b835b81811015611acf57803567ffffffffffffffff811115611aad57611aac6114cf565b5b808601611aba89826115e7565b85526020850194505050602081019050611a8a565b5050509392505050565b600082601f830112611aee57611aed6114cf565b5b8135611afe848260208601611a52565b91505092915050565b600060208284031215611b1d57611b1c6114c5565b5b600082013567ffffffffffffffff811115611b3b57611b3a6114ca565b5b611b4784828501611ad9565b91505092915050565b60006080830160008301518482036000860152611b6d82826117b4565b91505060208301518482036020860152611b8782826117b4565b9150506040830151611b9c60408601826117ed565b506060830151611baf60608601826117ed565b508091505092915050565b60006020820190508181036000830152611bd48184611b50565b905092915050565b600060208284031215611bf257611bf16114c5565b5b600082013567ffffffffffffffff811115611c1057611c0f6114ca565b5b611c1c848285016115e7565b91505092915050565b600060208284031215611c3b57611c3a6114c5565b5b6000611c49848285016116e4565b91505092915050565b600082825260208201905092915050565b6000611c6e82611765565b611c788185611c52565b9350611c88818560208601611781565b611c91816114d9565b840191505092915050565b60006020820190508181036000830152611cb68184611c63565b905092915050565b611cc7816116c3565b82525050565b60006080820190508181036000830152611ce78187611c63565b90508181036020830152611cfb8186611c63565b9050611d0a6040830185611cbe565b611d176060830184611cbe565b95945050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000611d89826116c3565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415611dbc57611dbb611d4f565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680611e0e57607f821691505b60208210811415611e2257611e21611dc7565b5b50919050565b600081905092915050565b60008190508160005260206000209050919050565b60008154611e5581611df6565b611e5f8186611e28565b94506001821660008114611e7a5760018114611e8b57611ebe565b60ff19831686528186019350611ebe565b611e9485611e33565b60005b83811015611eb657815481890152600182019150602081019050611e97565b838801955050505b50505092915050565b6000611ed38284611e48565b915081905092915050565b6000611ee9826116c3565b9150611ef4836116c3565b925082821015611f0757611f06611d4f565b5b828203905092915050565b6000611f1d826116c3565b9150611f28836116c3565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611f5d57611f5c611d4f565b5b828201905092915050565b6000611f7382611765565b611f7d8185611e28565b9350611f8d818560208601611781565b80840191505092915050565b6000611fa58284611f68565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea264697066735822122046173d57f903d3dd54c1f03acc5c922c87f780f9fca4382bd8625e6ecd4e630064736f6c634300080a0033";

    public static final String FUNC_BATCHDELETE = "batchDelete";

    public static final String FUNC_DELETEUSER = "deleteUser";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_GETLISTPAGE = "getListPage";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVESPEECHKEYATINDEX = "removeSpeechKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_SPEECHINSERTED = "speechInserted";

    public static final String FUNC_SPEECHKEY = "speechKey";

    public static final String FUNC_SPEECHS = "speechs";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected SpeechContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SpeechContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SpeechContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SpeechContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> deleteUser(Speech _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEUSER,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Speech>>() {}));
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

    public RemoteFunctionCall<Speech> getEntity(Speech _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_user),
                Arrays.<TypeReference<?>>asList(new TypeReference<Speech>() {}));
        return executeRemoteCallSingleValueReturn(function, Speech.class);
    }

    public RemoteFunctionCall<List> getListPage(BigInteger begin, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTPAGE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(begin),
                        new org.web3j.abi.datatypes.generated.Uint256(end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Speech>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> removeSpeechKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVESPEECHKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(Speech _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> speechInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SPEECHINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> speechKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SPEECHKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<String, String, BigInteger, BigInteger>> speechs(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SPEECHS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static SpeechContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SpeechContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SpeechContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SpeechContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SpeechContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SpeechContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SpeechContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SpeechContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SpeechContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SpeechContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SpeechContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SpeechContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SpeechContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SpeechContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SpeechContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SpeechContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Speech extends DynamicStruct {
        public String id;

        public String speechName;

        public BigInteger acquireTime;

        public BigInteger proofPic;

        public Speech(String id, String speechName, BigInteger acquireTime, BigInteger proofPic) {
            super(new org.web3j.abi.datatypes.Utf8String(id),new org.web3j.abi.datatypes.Utf8String(speechName),new org.web3j.abi.datatypes.generated.Uint256(acquireTime),new org.web3j.abi.datatypes.generated.Uint256(proofPic));
            this.id = id;
            this.speechName = speechName;
            this.acquireTime = acquireTime;
            this.proofPic = proofPic;
        }

        public Speech(Utf8String id, Utf8String speechName, Uint256 acquireTime, Uint256 proofPic) {
            super(id,speechName,acquireTime,proofPic);
            this.id = id.getValue();
            this.speechName = speechName.getValue();
            this.acquireTime = acquireTime.getValue();
            this.proofPic = proofPic.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}