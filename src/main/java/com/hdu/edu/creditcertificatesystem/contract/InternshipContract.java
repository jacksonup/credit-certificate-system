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
@SuppressWarnings("rawtypes")
public class InternshipContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b50612613806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806394c33b7d1161007157806394c33b7d14610178578063b10c3cc914610194578063d7251bb8146101c4578063ea0eeebf146101e0578063ed3a456014610215578063ee59a52914610231576100a9565b806326e5dbee146100ae578063465c4105146100de578063485c79471461010e57806353ed51431461013e57806359583c2a1461015c575b600080fd5b6100c860048036038101906100c39190611a11565b610261565b6040516100d59190611ad7565b60405180910390f35b6100f860048036038101906100f39190611c2e565b61030d565b6040516101059190611cc1565b60405180910390f35b61012860048036038101906101239190611cdc565b6103ea565b6040516101359190611ed5565b60405180910390f35b61014661084d565b6040516101539190611ed5565b60405180910390f35b61017660048036038101906101719190611fdd565b610b91565b005b610192600480360381019061018d9190612140565b610dca565b005b6101ae60048036038101906101a99190612140565b610ff5565b6040516101bb9190612227565b60405180910390f35b6101de60048036038101906101d99190612140565b6112c8565b005b6101fa60048036038101906101f59190612249565b61149f565b60405161020c969594939291906122a1565b60405180910390f35b61022f600480360381019061022a9190611a11565b611711565b005b61024b60048036038101906102469190612249565b6117eb565b6040516102589190611cc1565b60405180910390f35b6002818154811061027157600080fd5b90600052602060002001600091509050805461028c9061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546102b89061234d565b80156103055780601f106102da57610100808354040283529160200191610305565b820191906000526020600020905b8154815290600101906020018083116102e857829003601f168201915b505050505081565b6000808390506000839050805182511461032c576000925050506103e4565b60005b82518110156103dc5781818151811061034b5761034a61237f565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191683828151811061038b5761038a61237f565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146103c957600093505050506103e4565b80806103d4906123dd565b91505061032f565b506001925050505b92915050565b6060600060028054905067ffffffffffffffff81111561040d5761040c611b03565b5b60405190808252806020026020018201604052801561044657816020015b610433611821565b81526020019060019003908161042b5790505b5090506000805b600280549050811015610739576000600282815481106104705761046f61237f565b5b9060005260206000200160405161048791906124c5565b90815260200160405180910390206040518060c00160405290816000820180546104b09061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546104dc9061234d565b80156105295780601f106104fe57610100808354040283529160200191610529565b820191906000526020600020905b81548152906001019060200180831161050c57829003601f168201915b505050505081526020016001820180546105429061234d565b80601f016020809104026020016040519081016040528092919081815260200182805461056e9061234d565b80156105bb5780601f10610590576101008083540402835291602001916105bb565b820191906000526020600020905b81548152906001019060200180831161059e57829003601f168201915b5050505050815260200160028201548152602001600382015481526020016004820180546105e89061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546106149061234d565b80156106615780601f1061063657610100808354040283529160200191610661565b820191906000526020600020905b81548152906001019060200180831161064457829003601f168201915b5050505050815260200160058201805461067a9061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546106a69061234d565b80156106f35780601f106106c8576101008083540402835291602001916106f3565b820191906000526020600020905b8154815290600101906020018083116106d657829003601f168201915b505050505081525050838380610708906123dd565b94508151811061071b5761071a61237f565b5b60200260200101819052508080610731906123dd565b91505061044d565b5084841015610749575050610847565b60018161075691906124dc565b84111561076d5760018161076a91906124dc565b93505b6001858561077b91906124dc565b6107859190612510565b67ffffffffffffffff81111561079e5761079d611b03565b5b6040519080825280602002602001820160405280156107d757816020015b6107c4611821565b8152602001906001900390816107bc5790505b5092506000905060008590505b848111610843578281815181106107fe576107fd61237f565b5b6020026020010151848380610812906123dd565b9450815181106108255761082461237f565b5b6020026020010181905250808061083b906123dd565b9150506107e4565b5050505b92915050565b606060028054905067ffffffffffffffff81111561086e5761086d611b03565b5b6040519080825280602002602001820160405280156108a757816020015b610894611821565b81526020019060019003908161088c5790505b50905060005b600280549050811015610b8d576000600282815481106108d0576108cf61237f565b5b906000526020600020016040516108e791906124c5565b90815260200160405180910390206040518060c00160405290816000820180546109109061234d565b80601f016020809104026020016040519081016040528092919081815260200182805461093c9061234d565b80156109895780601f1061095e57610100808354040283529160200191610989565b820191906000526020600020905b81548152906001019060200180831161096c57829003601f168201915b505050505081526020016001820180546109a29061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546109ce9061234d565b8015610a1b5780601f106109f057610100808354040283529160200191610a1b565b820191906000526020600020905b8154815290600101906020018083116109fe57829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482018054610a489061234d565b80601f0160208091040260200160405190810160405280929190818152602001828054610a749061234d565b8015610ac15780601f10610a9657610100808354040283529160200191610ac1565b820191906000526020600020905b815481529060010190602001808311610aa457829003601f168201915b50505050508152602001600582018054610ada9061234d565b80601f0160208091040260200160405190810160405280929190818152602001828054610b069061234d565b8015610b535780601f10610b2857610100808354040283529160200191610b53565b820191906000526020600020905b815481529060010190602001808311610b3657829003601f168201915b505050505081525050828281518110610b6f57610b6e61237f565b5b60200260200101819052508080610b85906123dd565b9150506108ad565b5090565b60005b8151811015610dc6576001828281518110610bb257610bb161237f565b5b6020026020010151604051610bc79190612597565b908152602001604051809103902060009054906101000a900460ff1615610d7a576000806001848481518110610c0057610bff61237f565b5b6020026020010151604051610c159190612597565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610d3357610d16838381518110610c6257610c6161237f565b5b602002602001015160028381548110610c7e57610c7d61237f565b5b906000526020600020018054610c939061234d565b80601f0160208091040260200160405190810160405280929190818152602001828054610cbf9061234d565b8015610d0c5780601f10610ce157610100808354040283529160200191610d0c565b820191906000526020600020905b815481529060010190602001808311610cef57829003601f168201915b505050505061030d565b15610d2057610d33565b8080610d2b906123dd565b915050610c3f565b610d3c81611711565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610d6c9190611cc1565b60405180910390a150610db3565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896000604051610daa9190611cc1565b60405180910390a15b8080610dbe906123dd565b915050610b94565b5050565b60018160000151604051610dde9190612597565b908152602001604051809103902060009054906101000a900460ff16610f43578060008260000151604051610e139190612597565b90815260200160405180910390206000820151816000019080519060200190610e3d929190611857565b506020820151816001019080519060200190610e5a929190611857565b5060408201518160020155606082015181600301556080820151816004019080519060200190610e8b929190611857565b5060a0820151816005019080519060200190610ea8929190611857565b509050506001808260000151604051610ec19190612597565b908152602001604051809103902060006101000a81548160ff02191690831515021790555060028160000151908060018154018082558091505060019003906000526020600020016000909190919091509080519060200190610f25929190611857565b5060036000815480929190610f39906123dd565b9190505550610ff2565b8060008260000151604051610f589190612597565b90815260200160405180910390206000820151816000019080519060200190610f82929190611857565b506020820151816001019080519060200190610f9f929190611857565b5060408201518160020155606082015181600301556080820151816004019080519060200190610fd0929190611857565b5060a0820151816005019080519060200190610fed929190611857565b509050505b50565b610ffd611821565b600182600001516040516110119190612597565b908152602001604051809103902060009054906101000a900460ff16156112c257600082600001516040516110469190612597565b90815260200160405180910390206040518060c001604052908160008201805461106f9061234d565b80601f016020809104026020016040519081016040528092919081815260200182805461109b9061234d565b80156110e85780601f106110bd576101008083540402835291602001916110e8565b820191906000526020600020905b8154815290600101906020018083116110cb57829003601f168201915b505050505081526020016001820180546111019061234d565b80601f016020809104026020016040519081016040528092919081815260200182805461112d9061234d565b801561117a5780601f1061114f5761010080835404028352916020019161117a565b820191906000526020600020905b81548152906001019060200180831161115d57829003601f168201915b5050505050815260200160028201548152602001600382015481526020016004820180546111a79061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546111d39061234d565b80156112205780601f106111f557610100808354040283529160200191611220565b820191906000526020600020905b81548152906001019060200180831161120357829003601f168201915b505050505081526020016005820180546112399061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546112659061234d565b80156112b25780601f10611287576101008083540402835291602001916112b2565b820191906000526020600020905b81548152906001019060200180831161129557829003601f168201915b50505050508152505090506112c3565b5b919050565b600181600001516040516112dc9190612597565b908152602001604051809103902060009054906101000a900460ff161561146357600080600183600001516040516113149190612597565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b60028054905081101561141c576113ff8260000151600283815481106113675761136661237f565b5b90600052602060002001805461137c9061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546113a89061234d565b80156113f55780601f106113ca576101008083540402835291602001916113f5565b820191906000526020600020905b8154815290600101906020018083116113d857829003601f168201915b505050505061030d565b156114095761141c565b8080611414906123dd565b91505061133e565b61142581611711565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960016040516114559190611cc1565b60405180910390a15061149c565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960006040516114939190611cc1565b60405180910390a15b50565b6000818051602081018201805184825260208301602085012081835280955050505050506000915090508060000180546114d89061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546115049061234d565b80156115515780601f1061152657610100808354040283529160200191611551565b820191906000526020600020905b81548152906001019060200180831161153457829003601f168201915b5050505050908060010180546115669061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546115929061234d565b80156115df5780601f106115b4576101008083540402835291602001916115df565b820191906000526020600020905b8154815290600101906020018083116115c257829003601f168201915b5050505050908060020154908060030154908060040180546116009061234d565b80601f016020809104026020016040519081016040528092919081815260200182805461162c9061234d565b80156116795780601f1061164e57610100808354040283529160200191611679565b820191906000526020600020905b81548152906001019060200180831161165c57829003601f168201915b50505050509080600501805461168e9061234d565b80601f01602080910402602001604051908101604052809291908181526020018280546116ba9061234d565b80156117075780601f106116dc57610100808354040283529160200191611707565b820191906000526020600020905b8154815290600101906020018083116116ea57829003601f168201915b5050505050905086565b6000600280549050905080821061172857506117e8565b60008290505b60018261173b91906124dc565b8110156117b55760026001826117519190612510565b815481106117625761176161237f565b5b90600052602060002001600282815481106117805761177f61237f565b5b906000526020600020019080546117969061234d565b6117a19291906118dd565b5080806117ad906123dd565b91505061172e565b5060028054806117c8576117c76125ae565b5b6001900381819060005260206000200160006117e4919061196a565b9055505b50565b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b6040518060c001604052806060815260200160608152602001600081526020016000815260200160608152602001606081525090565b8280546118639061234d565b90600052602060002090601f01602090048101928261188557600085556118cc565b82601f1061189e57805160ff19168380011785556118cc565b828001600101855582156118cc579182015b828111156118cb5782518255916020019190600101906118b0565b5b5090506118d991906119aa565b5090565b8280546118e99061234d565b90600052602060002090601f01602090048101928261190b5760008555611959565b82601f1061191c5780548555611959565b8280016001018555821561195957600052602060002091601f016020900482015b8281111561195857825482559160010191906001019061193d565b5b50905061196691906119aa565b5090565b5080546119769061234d565b6000825580601f1061198857506119a7565b601f0160209004906000526020600020908101906119a691906119aa565b5b50565b5b808211156119c35760008160009055506001016119ab565b5090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b6119ee816119db565b81146119f957600080fd5b50565b600081359050611a0b816119e5565b92915050565b600060208284031215611a2757611a266119d1565b5b6000611a35848285016119fc565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611a78578082015181840152602081019050611a5d565b83811115611a87576000848401525b50505050565b6000601f19601f8301169050919050565b6000611aa982611a3e565b611ab38185611a49565b9350611ac3818560208601611a5a565b611acc81611a8d565b840191505092915050565b60006020820190508181036000830152611af18184611a9e565b905092915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611b3b82611a8d565b810181811067ffffffffffffffff82111715611b5a57611b59611b03565b5b80604052505050565b6000611b6d6119c7565b9050611b798282611b32565b919050565b600067ffffffffffffffff821115611b9957611b98611b03565b5b611ba282611a8d565b9050602081019050919050565b82818337600083830152505050565b6000611bd1611bcc84611b7e565b611b63565b905082815260208101848484011115611bed57611bec611afe565b5b611bf8848285611baf565b509392505050565b600082601f830112611c1557611c14611af9565b5b8135611c25848260208601611bbe565b91505092915050565b60008060408385031215611c4557611c446119d1565b5b600083013567ffffffffffffffff811115611c6357611c626119d6565b5b611c6f85828601611c00565b925050602083013567ffffffffffffffff811115611c9057611c8f6119d6565b5b611c9c85828601611c00565b9150509250929050565b60008115159050919050565b611cbb81611ca6565b82525050565b6000602082019050611cd66000830184611cb2565b92915050565b60008060408385031215611cf357611cf26119d1565b5b6000611d01858286016119fc565b9250506020611d12858286016119fc565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600082825260208201905092915050565b6000611d6482611a3e565b611d6e8185611d48565b9350611d7e818560208601611a5a565b611d8781611a8d565b840191505092915050565b611d9b816119db565b82525050565b600060c0830160008301518482036000860152611dbe8282611d59565b91505060208301518482036020860152611dd88282611d59565b9150506040830151611ded6040860182611d92565b506060830151611e006060860182611d92565b5060808301518482036080860152611e188282611d59565b91505060a083015184820360a0860152611e328282611d59565b9150508091505092915050565b6000611e4b8383611da1565b905092915050565b6000602082019050919050565b6000611e6b82611d1c565b611e758185611d27565b935083602082028501611e8785611d38565b8060005b85811015611ec35784840389528151611ea48582611e3f565b9450611eaf83611e53565b925060208a01995050600181019050611e8b565b50829750879550505050505092915050565b60006020820190508181036000830152611eef8184611e60565b905092915050565b600067ffffffffffffffff821115611f1257611f11611b03565b5b602082029050602081019050919050565b600080fd5b6000611f3b611f3684611ef7565b611b63565b90508083825260208201905060208402830185811115611f5e57611f5d611f23565b5b835b81811015611fa557803567ffffffffffffffff811115611f8357611f82611af9565b5b808601611f908982611c00565b85526020850194505050602081019050611f60565b5050509392505050565b600082601f830112611fc457611fc3611af9565b5b8135611fd4848260208601611f28565b91505092915050565b600060208284031215611ff357611ff26119d1565b5b600082013567ffffffffffffffff811115612011576120106119d6565b5b61201d84828501611faf565b91505092915050565b600080fd5b600080fd5b600060c0828403121561204657612045612026565b5b61205060c0611b63565b9050600082013567ffffffffffffffff8111156120705761206f61202b565b5b61207c84828501611c00565b600083015250602082013567ffffffffffffffff8111156120a05761209f61202b565b5b6120ac84828501611c00565b60208301525060406120c0848285016119fc565b60408301525060606120d4848285016119fc565b606083015250608082013567ffffffffffffffff8111156120f8576120f761202b565b5b61210484828501611c00565b60808301525060a082013567ffffffffffffffff8111156121285761212761202b565b5b61213484828501611c00565b60a08301525092915050565b600060208284031215612156576121556119d1565b5b600082013567ffffffffffffffff811115612174576121736119d6565b5b61218084828501612030565b91505092915050565b600060c08301600083015184820360008601526121a68282611d59565b915050602083015184820360208601526121c08282611d59565b91505060408301516121d56040860182611d92565b5060608301516121e86060860182611d92565b50608083015184820360808601526122008282611d59565b91505060a083015184820360a086015261221a8282611d59565b9150508091505092915050565b600060208201905081810360008301526122418184612189565b905092915050565b60006020828403121561225f5761225e6119d1565b5b600082013567ffffffffffffffff81111561227d5761227c6119d6565b5b61228984828501611c00565b91505092915050565b61229b816119db565b82525050565b600060c08201905081810360008301526122bb8189611a9e565b905081810360208301526122cf8188611a9e565b90506122de6040830187612292565b6122eb6060830186612292565b81810360808301526122fd8185611a9e565b905081810360a08301526123118184611a9e565b9050979650505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061236557607f821691505b602082108114156123795761237861231e565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006123e8826119db565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561241b5761241a6123ae565b5b600182019050919050565b600081905092915050565b60008190508160005260206000209050919050565b600081546124538161234d565b61245d8186612426565b945060018216600081146124785760018114612489576124bc565b60ff198316865281860193506124bc565b61249285612431565b60005b838110156124b457815481890152600182019150602081019050612495565b838801955050505b50505092915050565b60006124d18284612446565b915081905092915050565b60006124e7826119db565b91506124f2836119db565b925082821015612505576125046123ae565b5b828203905092915050565b600061251b826119db565b9150612526836119db565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561255b5761255a6123ae565b5b828201905092915050565b600061257182611a3e565b61257b8185612426565b935061258b818560208601611a5a565b80840191505092915050565b60006125a38284612566565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea2646970667358221220205f6a021a362ecb2f75d5db1ad1c3905d59369e330f44b41c9b1552037c57ed64736f6c634300080a0033";

    public static final String FUNC_BATCHDELETE = "batchDelete";

    public static final String FUNC_DELETEINTERNSHIP = "deleteInternship";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_GETLISTPAGE = "getListPage";

    public static final String FUNC_INTERNSHIPINSERTED = "internshipInserted";

    public static final String FUNC_INTERNSHIPKEY = "internshipKey";

    public static final String FUNC_INTERNSHIPS = "internships";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVEINTERNSHIPKEYATINDEX = "removeInternshipKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected InternshipContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected InternshipContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected InternshipContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected InternshipContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> deleteInternship(Internship _internship) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEINTERNSHIP,
                Arrays.<Type>asList(_internship),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Internship>>() {}));
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

    public RemoteFunctionCall<Internship> getEntity(Internship _internship) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_internship),
                Arrays.<TypeReference<?>>asList(new TypeReference<Internship>() {}));
        return executeRemoteCallSingleValueReturn(function, Internship.class);
    }

    public RemoteFunctionCall<List> getListPage(BigInteger begin, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTPAGE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(begin),
                        new org.web3j.abi.datatypes.generated.Uint256(end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Internship>>() {}));
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

    public RemoteFunctionCall<Boolean> internshipInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTERNSHIPINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> internshipKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTERNSHIPKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple6<String, String, BigInteger, BigInteger, String, String>> internships(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTERNSHIPS,
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

    public RemoteFunctionCall<Boolean> isEqual(String a, String b) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISEQUAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a),
                        new org.web3j.abi.datatypes.Utf8String(b)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeInternshipKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEINTERNSHIPKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(Internship _internship) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_internship),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static InternshipContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new InternshipContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static InternshipContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new InternshipContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static InternshipContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new InternshipContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static InternshipContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new InternshipContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<InternshipContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(InternshipContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<InternshipContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(InternshipContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<InternshipContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(InternshipContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<InternshipContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(InternshipContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Internship extends DynamicStruct {
        public String id;

        public String companyName;

        public BigInteger startTime;

        public BigInteger endTime;

        public String internshipProofPic;

        public String evaluation;

        public Internship(String id, String companyName, BigInteger startTime, BigInteger endTime, String internshipProofPic, String evaluation) {
            super(new org.web3j.abi.datatypes.Utf8String(id),new org.web3j.abi.datatypes.Utf8String(companyName),new org.web3j.abi.datatypes.generated.Uint256(startTime),new org.web3j.abi.datatypes.generated.Uint256(endTime),new org.web3j.abi.datatypes.Utf8String(internshipProofPic),new org.web3j.abi.datatypes.Utf8String(evaluation));
            this.id = id;
            this.companyName = companyName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.internshipProofPic = internshipProofPic;
            this.evaluation = evaluation;
        }

        public Internship(Utf8String id, Utf8String companyName, Uint256 startTime, Uint256 endTime, Utf8String internshipProofPic, Utf8String evaluation) {
            super(id,companyName,startTime,endTime,internshipProofPic,evaluation);
            this.id = id.getValue();
            this.companyName = companyName.getValue();
            this.startTime = startTime.getValue();
            this.endTime = endTime.getValue();
            this.internshipProofPic = internshipProofPic.getValue();
            this.evaluation = evaluation.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}