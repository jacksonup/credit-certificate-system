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
public class VolunteerContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b50612314806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806353ed51431161007157806353ed51431461018e57806359583c2a146101ac5780635baf0738146101c8578063754ea4ba146101e45780638a34c2e414610214578063e8ca326014610244576100a9565b806314bb6703146100ae5780632b086ab6146100de578063465c4105146100fa578063474a3cb01461012a578063485c79471461015e575b600080fd5b6100c860048036038101906100c391906119bb565b610260565b6040516100d59190611b1f565b60405180910390f35b6100f860048036038101906100f391906119bb565b6104a1565b005b610114600480360381019061010f9190611b41565b610692565b6040516101219190611bd4565b60405180910390f35b610144600480360381019061013f9190611bef565b61076f565b604051610155959493929190611c91565b60405180910390f35b61017860048036038101906101739190611cf9565b610953565b6040516101859190611e7f565b60405180910390f35b610196610d24565b6040516101a39190611e7f565b60405180910390f35b6101c660048036038101906101c19190611f87565b610fd6565b005b6101e260048036038101906101dd91906119bb565b61120f565b005b6101fe60048036038101906101f99190611bef565b6113e6565b60405161020b9190611bd4565b60405180910390f35b61022e60048036038101906102299190611fd0565b61141c565b60405161023b9190611ffd565b60405180910390f35b61025e60048036038101906102599190611fd0565b6114c8565b005b6102686115a2565b6001826000015160405161027c919061205b565b908152602001604051809103902060009054906101000a900460ff161561049b57600082600001516040516102b1919061205b565b90815260200160405180910390206040518060a00160405290816000820180546102da906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610306906120a1565b80156103535780601f1061032857610100808354040283529160200191610353565b820191906000526020600020905b81548152906001019060200180831161033657829003601f168201915b5050505050815260200160018201805461036c906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610398906120a1565b80156103e55780601f106103ba576101008083540402835291602001916103e5565b820191906000526020600020905b8154815290600101906020018083116103c857829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482018054610412906120a1565b80601f016020809104026020016040519081016040528092919081815260200182805461043e906120a1565b801561048b5780601f106104605761010080835404028352916020019161048b565b820191906000526020600020905b81548152906001019060200180831161046e57829003601f168201915b505050505081525050905061049c565b5b919050565b600181600001516040516104b5919061205b565b908152602001604051809103902060009054906101000a900460ff166105fd5780600082600001516040516104ea919061205b565b908152602001604051809103902060008201518160000190805190602001906105149291906115d1565b5060208201518160010190805190602001906105319291906115d1565b50604082015181600201556060820151816003015560808201518160040190805190602001906105629291906115d1565b50905050600180826000015160405161057b919061205b565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600281600001519080600181540180825580915050600190039060005260206000200160009091909190915090805190602001906105df9291906115d1565b50600360008154809291906105f390612102565b919050555061068f565b8060008260000151604051610612919061205b565b9081526020016040518091039020600082015181600001908051906020019061063c9291906115d1565b5060208201518160010190805190602001906106599291906115d1565b506040820151816002015560608201518160030155608082015181600401908051906020019061068a9291906115d1565b509050505b50565b600080839050600083905080518251146106b157600092505050610769565b60005b8251811015610761578181815181106106d0576106cf61214b565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168382815181106107105761070f61214b565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161461074e5760009350505050610769565b808061075990612102565b9150506106b4565b506001925050505b92915050565b6000818051602081018201805184825260208301602085012081835280955050505050506000915090508060000180546107a8906120a1565b80601f01602080910402602001604051908101604052809291908181526020018280546107d4906120a1565b80156108215780601f106107f657610100808354040283529160200191610821565b820191906000526020600020905b81548152906001019060200180831161080457829003601f168201915b505050505090806001018054610836906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610862906120a1565b80156108af5780601f10610884576101008083540402835291602001916108af565b820191906000526020600020905b81548152906001019060200180831161089257829003601f168201915b5050505050908060020154908060030154908060040180546108d0906120a1565b80601f01602080910402602001604051908101604052809291908181526020018280546108fc906120a1565b80156109495780601f1061091e57610100808354040283529160200191610949565b820191906000526020600020905b81548152906001019060200180831161092c57829003601f168201915b5050505050905085565b6060600060028054905067ffffffffffffffff8111156109765761097561176b565b5b6040519080825280602002602001820160405280156109af57816020015b61099c6115a2565b8152602001906001900390816109945790505b5090506000805b600280549050811015610c10576000600282815481106109d9576109d861214b565b5b906000526020600020016040516109f0919061220e565b90815260200160405180910390206040518060a0016040529081600082018054610a19906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610a45906120a1565b8015610a925780601f10610a6757610100808354040283529160200191610a92565b820191906000526020600020905b815481529060010190602001808311610a7557829003601f168201915b50505050508152602001600182018054610aab906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610ad7906120a1565b8015610b245780601f10610af957610100808354040283529160200191610b24565b820191906000526020600020905b815481529060010190602001808311610b0757829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482018054610b51906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610b7d906120a1565b8015610bca5780601f10610b9f57610100808354040283529160200191610bca565b820191906000526020600020905b815481529060010190602001808311610bad57829003601f168201915b505050505081525050838380610bdf90612102565b945081518110610bf257610bf161214b565b5b60200260200101819052508080610c0890612102565b9150506109b6565b5084841015610c20575050610d1e565b600181610c2d9190612225565b841115610c4457600181610c419190612225565b93505b60018585610c529190612225565b610c5c9190612259565b67ffffffffffffffff811115610c7557610c7461176b565b5b604051908082528060200260200182016040528015610cae57816020015b610c9b6115a2565b815260200190600190039081610c935790505b5092506000905060008590505b848111610d1a57828181518110610cd557610cd461214b565b5b6020026020010151848380610ce990612102565b945081518110610cfc57610cfb61214b565b5b60200260200101819052508080610d1290612102565b915050610cbb565b5050505b92915050565b606060028054905067ffffffffffffffff811115610d4557610d4461176b565b5b604051908082528060200260200182016040528015610d7e57816020015b610d6b6115a2565b815260200190600190039081610d635790505b50905060005b600280549050811015610fd257600060028281548110610da757610da661214b565b5b90600052602060002001604051610dbe919061220e565b90815260200160405180910390206040518060a0016040529081600082018054610de7906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610e13906120a1565b8015610e605780601f10610e3557610100808354040283529160200191610e60565b820191906000526020600020905b815481529060010190602001808311610e4357829003601f168201915b50505050508152602001600182018054610e79906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610ea5906120a1565b8015610ef25780601f10610ec757610100808354040283529160200191610ef2565b820191906000526020600020905b815481529060010190602001808311610ed557829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482018054610f1f906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054610f4b906120a1565b8015610f985780601f10610f6d57610100808354040283529160200191610f98565b820191906000526020600020905b815481529060010190602001808311610f7b57829003601f168201915b505050505081525050828281518110610fb457610fb361214b565b5b60200260200101819052508080610fca90612102565b915050610d84565b5090565b60005b815181101561120b576001828281518110610ff757610ff661214b565b5b602002602001015160405161100c919061205b565b908152602001604051809103902060009054906101000a900460ff16156111bf5760008060018484815181106110455761104461214b565b5b602002602001015160405161105a919061205b565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b6002805490508110156111785761115b8383815181106110a7576110a661214b565b5b6020026020010151600283815481106110c3576110c261214b565b5b9060005260206000200180546110d8906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054611104906120a1565b80156111515780601f1061112657610100808354040283529160200191611151565b820191906000526020600020905b81548152906001019060200180831161113457829003601f168201915b5050505050610692565b1561116557611178565b808061117090612102565b915050611084565b611181816114c8565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960016040516111b19190611bd4565b60405180910390a1506111f8565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960006040516111ef9190611bd4565b60405180910390a15b808061120390612102565b915050610fd9565b5050565b60018160000151604051611223919061205b565b908152602001604051809103902060009054906101000a900460ff16156113aa576000806001836000015160405161125b919061205b565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015611363576113468260000151600283815481106112ae576112ad61214b565b5b9060005260206000200180546112c3906120a1565b80601f01602080910402602001604051908101604052809291908181526020018280546112ef906120a1565b801561133c5780601f106113115761010080835404028352916020019161133c565b820191906000526020600020905b81548152906001019060200180831161131f57829003601f168201915b5050505050610692565b1561135057611363565b808061135b90612102565b915050611285565b61136c816114c8565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a89600160405161139c9190611bd4565b60405180910390a1506113e3565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960006040516113da9190611bd4565b60405180910390a15b50565b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b6002818154811061142c57600080fd5b906000526020600020016000915090508054611447906120a1565b80601f0160208091040260200160405190810160405280929190818152602001828054611473906120a1565b80156114c05780601f10611495576101008083540402835291602001916114c0565b820191906000526020600020905b8154815290600101906020018083116114a357829003601f168201915b505050505081565b600060028054905090508082106114df575061159f565b60008290505b6001826114f29190612225565b81101561156c5760026001826115089190612259565b815481106115195761151861214b565b5b90600052602060002001600282815481106115375761153661214b565b5b9060005260206000200190805461154d906120a1565b611558929190611657565b50808061156490612102565b9150506114e5565b50600280548061157f5761157e6122af565b5b60019003818190600052602060002001600061159b91906116e4565b9055505b50565b6040518060a0016040528060608152602001606081526020016000815260200160008152602001606081525090565b8280546115dd906120a1565b90600052602060002090601f0160209004810192826115ff5760008555611646565b82601f1061161857805160ff1916838001178555611646565b82800160010185558215611646579182015b8281111561164557825182559160200191906001019061162a565b5b5090506116539190611724565b5090565b828054611663906120a1565b90600052602060002090601f01602090048101928261168557600085556116d3565b82601f1061169657805485556116d3565b828001600101855582156116d357600052602060002091601f016020900482015b828111156116d25782548255916001019190600101906116b7565b5b5090506116e09190611724565b5090565b5080546116f0906120a1565b6000825580601f106117025750611721565b601f0160209004906000526020600020908101906117209190611724565b5b50565b5b8082111561173d576000816000905550600101611725565b5090565b6000604051905090565b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6117a38261175a565b810181811067ffffffffffffffff821117156117c2576117c161176b565b5b80604052505050565b60006117d5611741565b90506117e1828261179a565b919050565b600080fd5b600080fd5b600080fd5b600067ffffffffffffffff8211156118105761180f61176b565b5b6118198261175a565b9050602081019050919050565b82818337600083830152505050565b6000611848611843846117f5565b6117cb565b905082815260208101848484011115611864576118636117f0565b5b61186f848285611826565b509392505050565b600082601f83011261188c5761188b6117eb565b5b813561189c848260208601611835565b91505092915050565b6000819050919050565b6118b8816118a5565b81146118c357600080fd5b50565b6000813590506118d5816118af565b92915050565b600060a082840312156118f1576118f0611755565b5b6118fb60a06117cb565b9050600082013567ffffffffffffffff81111561191b5761191a6117e6565b5b61192784828501611877565b600083015250602082013567ffffffffffffffff81111561194b5761194a6117e6565b5b61195784828501611877565b602083015250604061196b848285016118c6565b604083015250606061197f848285016118c6565b606083015250608082013567ffffffffffffffff8111156119a3576119a26117e6565b5b6119af84828501611877565b60808301525092915050565b6000602082840312156119d1576119d061174b565b5b600082013567ffffffffffffffff8111156119ef576119ee611750565b5b6119fb848285016118db565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611a3e578082015181840152602081019050611a23565b83811115611a4d576000848401525b50505050565b6000611a5e82611a04565b611a688185611a0f565b9350611a78818560208601611a20565b611a818161175a565b840191505092915050565b611a95816118a5565b82525050565b600060a0830160008301518482036000860152611ab88282611a53565b91505060208301518482036020860152611ad28282611a53565b9150506040830151611ae76040860182611a8c565b506060830151611afa6060860182611a8c565b5060808301518482036080860152611b128282611a53565b9150508091505092915050565b60006020820190508181036000830152611b398184611a9b565b905092915050565b60008060408385031215611b5857611b5761174b565b5b600083013567ffffffffffffffff811115611b7657611b75611750565b5b611b8285828601611877565b925050602083013567ffffffffffffffff811115611ba357611ba2611750565b5b611baf85828601611877565b9150509250929050565b60008115159050919050565b611bce81611bb9565b82525050565b6000602082019050611be96000830184611bc5565b92915050565b600060208284031215611c0557611c0461174b565b5b600082013567ffffffffffffffff811115611c2357611c22611750565b5b611c2f84828501611877565b91505092915050565b600082825260208201905092915050565b6000611c5482611a04565b611c5e8185611c38565b9350611c6e818560208601611a20565b611c778161175a565b840191505092915050565b611c8b816118a5565b82525050565b600060a0820190508181036000830152611cab8188611c49565b90508181036020830152611cbf8187611c49565b9050611cce6040830186611c82565b611cdb6060830185611c82565b8181036080830152611ced8184611c49565b90509695505050505050565b60008060408385031215611d1057611d0f61174b565b5b6000611d1e858286016118c6565b9250506020611d2f858286016118c6565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600060a0830160008301518482036000860152611d828282611a53565b91505060208301518482036020860152611d9c8282611a53565b9150506040830151611db16040860182611a8c565b506060830151611dc46060860182611a8c565b5060808301518482036080860152611ddc8282611a53565b9150508091505092915050565b6000611df58383611d65565b905092915050565b6000602082019050919050565b6000611e1582611d39565b611e1f8185611d44565b935083602082028501611e3185611d55565b8060005b85811015611e6d5784840389528151611e4e8582611de9565b9450611e5983611dfd565b925060208a01995050600181019050611e35565b50829750879550505050505092915050565b60006020820190508181036000830152611e998184611e0a565b905092915050565b600067ffffffffffffffff821115611ebc57611ebb61176b565b5b602082029050602081019050919050565b600080fd5b6000611ee5611ee084611ea1565b6117cb565b90508083825260208201905060208402830185811115611f0857611f07611ecd565b5b835b81811015611f4f57803567ffffffffffffffff811115611f2d57611f2c6117eb565b5b808601611f3a8982611877565b85526020850194505050602081019050611f0a565b5050509392505050565b600082601f830112611f6e57611f6d6117eb565b5b8135611f7e848260208601611ed2565b91505092915050565b600060208284031215611f9d57611f9c61174b565b5b600082013567ffffffffffffffff811115611fbb57611fba611750565b5b611fc784828501611f59565b91505092915050565b600060208284031215611fe657611fe561174b565b5b6000611ff4848285016118c6565b91505092915050565b600060208201905081810360008301526120178184611c49565b905092915050565b600081905092915050565b600061203582611a04565b61203f818561201f565b935061204f818560208601611a20565b80840191505092915050565b6000612067828461202a565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806120b957607f821691505b602082108114156120cd576120cc612072565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061210d826118a5565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156121405761213f6120d3565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b60008190508160005260206000209050919050565b6000815461219c816120a1565b6121a6818661201f565b945060018216600081146121c157600181146121d257612205565b60ff19831686528186019350612205565b6121db8561217a565b60005b838110156121fd578154818901526001820191506020810190506121de565b838801955050505b50505092915050565b600061221a828461218f565b915081905092915050565b6000612230826118a5565b915061223b836118a5565b92508282101561224e5761224d6120d3565b5b828203905092915050565b6000612264826118a5565b915061226f836118a5565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff038211156122a4576122a36120d3565b5b828201905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea2646970667358221220a9fe31aa18c56db2e6051b219d855c105bedaf21e00d456e56ef2fb39da351ed64736f6c634300080a0033";

    public static final String FUNC_BATCHDELETE = "batchDelete";

    public static final String FUNC_DELETEUSER = "deleteUser";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_GETLISTPAGE = "getListPage";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVEVOLUNTEERKEYATINDEX = "removeVolunteerKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_VOLUNTEERINSERTED = "volunteerInserted";

    public static final String FUNC_VOLUNTEERKEY = "volunteerKey";

    public static final String FUNC_VOLUNTEERS = "volunteers";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected VolunteerContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VolunteerContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VolunteerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VolunteerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> deleteUser(Volunteer _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEUSER,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Volunteer>>() {}));
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

    public RemoteFunctionCall<Volunteer> getEntity(Volunteer _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_user),
                Arrays.<TypeReference<?>>asList(new TypeReference<Volunteer>() {}));
        return executeRemoteCallSingleValueReturn(function, Volunteer.class);
    }

    public RemoteFunctionCall<List> getListPage(BigInteger begin, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTPAGE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(begin),
                        new org.web3j.abi.datatypes.generated.Uint256(end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Volunteer>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> removeVolunteerKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEVOLUNTEERKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(Volunteer _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_user),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> volunteerInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOLUNTEERINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> volunteerKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOLUNTEERKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, String>> volunteers(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOLUNTEERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, String>>(function,
                new Callable<Tuple5<String, String, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, BigInteger, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (String) results.get(4).getValue());
                    }
                });
    }

    @Deprecated
    public static VolunteerContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VolunteerContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VolunteerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VolunteerContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VolunteerContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VolunteerContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VolunteerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VolunteerContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VolunteerContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VolunteerContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VolunteerContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VolunteerContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<VolunteerContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VolunteerContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VolunteerContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VolunteerContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Volunteer extends DynamicStruct {
        public String id;

        public String volunteerName;

        public BigInteger volunteerTime;

        public BigInteger acquireTime;

        public String proofPic;

        public Volunteer(String id, String volunteerName, BigInteger volunteerTime, BigInteger acquireTime, String proofPic) {
            super(new org.web3j.abi.datatypes.Utf8String(id),new org.web3j.abi.datatypes.Utf8String(volunteerName),new org.web3j.abi.datatypes.generated.Uint256(volunteerTime),new org.web3j.abi.datatypes.generated.Uint256(acquireTime),new org.web3j.abi.datatypes.Utf8String(proofPic));
            this.id = id;
            this.volunteerName = volunteerName;
            this.volunteerTime = volunteerTime;
            this.acquireTime = acquireTime;
            this.proofPic = proofPic;
        }

        public Volunteer(Utf8String id, Utf8String volunteerName, Uint256 volunteerTime, Uint256 acquireTime, Utf8String proofPic) {
            super(id,volunteerName,volunteerTime,acquireTime,proofPic);
            this.id = id.getValue();
            this.volunteerName = volunteerName.getValue();
            this.volunteerTime = volunteerTime.getValue();
            this.acquireTime = acquireTime.getValue();
            this.proofPic = proofPic.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}