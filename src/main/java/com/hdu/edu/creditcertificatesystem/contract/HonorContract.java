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
public class HonorContract extends Contract {
    public static final String BINARY = "608060405260028054905060035534801561001957600080fd5b5061258b806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80635a9278ae116100715780635a9278ae14610164578063675e19ef14610198578063b9749c88146101c8578063c30cb0f6146101e4578063de40be3014610214578063f2a45aad14610230576100a9565b80632bd4ce85146100ae578063465c4105146100ca578063485c7947146100fa57806353ed51431461012a57806359583c2a14610148575b600080fd5b6100c860048036038101906100c39190611c1d565b610260565b005b6100e460048036038101906100df9190611c66565b610437565b6040516100f19190611cf9565b60405180910390f35b610114600480360381019061010f9190611d14565b610514565b6040516101219190611f38565b60405180910390f35b61013261096d565b60405161013f9190611f38565b60405180910390f35b610162600480360381019061015d9190612040565b610ca7565b005b61017e60048036038101906101799190612089565b610ee0565b60405161018f95949392919061212b565b60405180910390f35b6101b260048036038101906101ad9190612089565b61114c565b6040516101bf9190611cf9565b60405180910390f35b6101e260048036038101906101dd919061219a565b611182565b005b6101fe60048036038101906101f9919061219a565b61125c565b60405161020b91906121c7565b60405180910390f35b61022e60048036038101906102299190611c1d565b611308565b005b61024a60048036038101906102459190611c1d565b61151f565b6040516102579190612274565b60405180910390f35b6001816000015160405161027491906122d2565b908152602001604051809103902060009054906101000a900460ff16156103fb57600080600183600001516040516102ac91906122d2565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b6002805490508110156103b4576103978260000151600283815481106102ff576102fe6122e9565b5b90600052602060002001805461031490612347565b80601f016020809104026020016040519081016040528092919081815260200182805461034090612347565b801561038d5780601f106103625761010080835404028352916020019161038d565b820191906000526020600020905b81548152906001019060200180831161037057829003601f168201915b5050505050610437565b156103a1576103b4565b80806103ac906123a8565b9150506102d6565b6103bd81611182565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a8960016040516103ed9190611cf9565b60405180910390a150610434565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a89600060405161042b9190611cf9565b60405180910390a15b50565b600080839050600083905080518251146104565760009250505061050e565b60005b825181101561050657818181518110610475576104746122e9565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168382815181106104b5576104b46122e9565b5b602001015160f81c60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146104f3576000935050505061050e565b80806104fe906123a8565b915050610459565b506001925050505b92915050565b6060600060028054905067ffffffffffffffff811115610537576105366119b1565b5b60405190808252806020026020018201604052801561057057816020015b61055d6117e8565b8152602001906001900390816105555790505b5090506000805b6002805490508110156108595760006002828154811061059a576105996122e9565b5b906000526020600020016040516105b19190612485565b90815260200160405180910390206040518060a00160405290816000820180546105da90612347565b80601f016020809104026020016040519081016040528092919081815260200182805461060690612347565b80156106535780601f1061062857610100808354040283529160200191610653565b820191906000526020600020905b81548152906001019060200180831161063657829003601f168201915b5050505050815260200160018201805461066c90612347565b80601f016020809104026020016040519081016040528092919081815260200182805461069890612347565b80156106e55780601f106106ba576101008083540402835291602001916106e5565b820191906000526020600020905b8154815290600101906020018083116106c857829003601f168201915b505050505081526020016002820180546106fe90612347565b80601f016020809104026020016040519081016040528092919081815260200182805461072a90612347565b80156107775780601f1061074c57610100808354040283529160200191610777565b820191906000526020600020905b81548152906001019060200180831161075a57829003601f168201915b505050505081526020016003820154815260200160048201805461079a90612347565b80601f01602080910402602001604051908101604052809291908181526020018280546107c690612347565b80156108135780601f106107e857610100808354040283529160200191610813565b820191906000526020600020905b8154815290600101906020018083116107f657829003601f168201915b505050505081525050838380610828906123a8565b94508151811061083b5761083a6122e9565b5b60200260200101819052508080610851906123a8565b915050610577565b5084841015610869575050610967565b600181610876919061249c565b84111561088d5760018161088a919061249c565b93505b6001858561089b919061249c565b6108a591906124d0565b67ffffffffffffffff8111156108be576108bd6119b1565b5b6040519080825280602002602001820160405280156108f757816020015b6108e46117e8565b8152602001906001900390816108dc5790505b5092506000905060008590505b8481116109635782818151811061091e5761091d6122e9565b5b6020026020010151848380610932906123a8565b945081518110610945576109446122e9565b5b6020026020010181905250808061095b906123a8565b915050610904565b5050505b92915050565b606060028054905067ffffffffffffffff81111561098e5761098d6119b1565b5b6040519080825280602002602001820160405280156109c757816020015b6109b46117e8565b8152602001906001900390816109ac5790505b50905060005b600280549050811015610ca3576000600282815481106109f0576109ef6122e9565b5b90600052602060002001604051610a079190612485565b90815260200160405180910390206040518060a0016040529081600082018054610a3090612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610a5c90612347565b8015610aa95780601f10610a7e57610100808354040283529160200191610aa9565b820191906000526020600020905b815481529060010190602001808311610a8c57829003601f168201915b50505050508152602001600182018054610ac290612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610aee90612347565b8015610b3b5780601f10610b1057610100808354040283529160200191610b3b565b820191906000526020600020905b815481529060010190602001808311610b1e57829003601f168201915b50505050508152602001600282018054610b5490612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610b8090612347565b8015610bcd5780601f10610ba257610100808354040283529160200191610bcd565b820191906000526020600020905b815481529060010190602001808311610bb057829003601f168201915b5050505050815260200160038201548152602001600482018054610bf090612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610c1c90612347565b8015610c695780601f10610c3e57610100808354040283529160200191610c69565b820191906000526020600020905b815481529060010190602001808311610c4c57829003601f168201915b505050505081525050828281518110610c8557610c846122e9565b5b60200260200101819052508080610c9b906123a8565b9150506109cd565b5090565b60005b8151811015610edc576001828281518110610cc857610cc76122e9565b5b6020026020010151604051610cdd91906122d2565b908152602001604051809103902060009054906101000a900460ff1615610e90576000806001848481518110610d1657610d156122e9565b5b6020026020010151604051610d2b91906122d2565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600090505b600280549050811015610e4957610e2c838381518110610d7857610d776122e9565b5b602002602001015160028381548110610d9457610d936122e9565b5b906000526020600020018054610da990612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610dd590612347565b8015610e225780601f10610df757610100808354040283529160200191610e22565b820191906000526020600020905b815481529060010190602001808311610e0557829003601f168201915b5050505050610437565b15610e3657610e49565b8080610e41906123a8565b915050610d55565b610e5281611182565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896001604051610e829190611cf9565b60405180910390a150610ec9565b7fcdb72d06565be2e6e4048e127d72c0d6cd72635cbae3d499c02100a7cd5c0a896000604051610ec09190611cf9565b60405180910390a15b8080610ed4906123a8565b915050610caa565b5050565b600081805160208101820180518482526020830160208501208183528095505050505050600091509050806000018054610f1990612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610f4590612347565b8015610f925780601f10610f6757610100808354040283529160200191610f92565b820191906000526020600020905b815481529060010190602001808311610f7557829003601f168201915b505050505090806001018054610fa790612347565b80601f0160208091040260200160405190810160405280929190818152602001828054610fd390612347565b80156110205780601f10610ff557610100808354040283529160200191611020565b820191906000526020600020905b81548152906001019060200180831161100357829003601f168201915b50505050509080600201805461103590612347565b80601f016020809104026020016040519081016040528092919081815260200182805461106190612347565b80156110ae5780601f10611083576101008083540402835291602001916110ae565b820191906000526020600020905b81548152906001019060200180831161109157829003601f168201915b5050505050908060030154908060040180546110c990612347565b80601f01602080910402602001604051908101604052809291908181526020018280546110f590612347565b80156111425780601f1061111757610100808354040283529160200191611142565b820191906000526020600020905b81548152906001019060200180831161112557829003601f168201915b5050505050905085565b6001818051602081018201805184825260208301602085012081835280955050505050506000915054906101000a900460ff1681565b600060028054905090508082106111995750611259565b60008290505b6001826111ac919061249c565b8110156112265760026001826111c291906124d0565b815481106111d3576111d26122e9565b5b90600052602060002001600282815481106111f1576111f06122e9565b5b9060005260206000200190805461120790612347565b611212929190611817565b50808061121e906123a8565b91505061119f565b50600280548061123957611238612526565b5b60019003818190600052602060002001600061125591906118a4565b9055505b50565b6002818154811061126c57600080fd5b90600052602060002001600091509050805461128790612347565b80601f01602080910402602001604051908101604052809291908181526020018280546112b390612347565b80156113005780601f106112d557610100808354040283529160200191611300565b820191906000526020600020905b8154815290600101906020018083116112e357829003601f168201915b505050505081565b6001816000015160405161131c91906122d2565b908152602001604051809103902060009054906101000a900460ff1661147757806000826000015160405161135191906122d2565b9081526020016040518091039020600082015181600001908051906020019061137b9291906118e4565b5060208201518160010190805190602001906113989291906118e4565b5060408201518160020190805190602001906113b59291906118e4565b506060820151816003015560808201518160040190805190602001906113dc9291906118e4565b5090505060018082600001516040516113f591906122d2565b908152602001604051809103902060006101000a81548160ff021916908315150217905550600281600001519080600181540180825580915050600190039060005260206000200160009091909190915090805190602001906114599291906118e4565b506003600081548092919061146d906123a8565b919050555061151c565b806000826000015160405161148c91906122d2565b908152602001604051809103902060008201518160000190805190602001906114b69291906118e4565b5060208201518160010190805190602001906114d39291906118e4565b5060408201518160020190805190602001906114f09291906118e4565b506060820151816003015560808201518160040190805190602001906115179291906118e4565b509050505b50565b6115276117e8565b6001826000015160405161153b91906122d2565b908152602001604051809103902060009054906101000a900460ff16156117e2576000826000015160405161157091906122d2565b90815260200160405180910390206040518060a001604052908160008201805461159990612347565b80601f01602080910402602001604051908101604052809291908181526020018280546115c590612347565b80156116125780601f106115e757610100808354040283529160200191611612565b820191906000526020600020905b8154815290600101906020018083116115f557829003601f168201915b5050505050815260200160018201805461162b90612347565b80601f016020809104026020016040519081016040528092919081815260200182805461165790612347565b80156116a45780601f10611679576101008083540402835291602001916116a4565b820191906000526020600020905b81548152906001019060200180831161168757829003601f168201915b505050505081526020016002820180546116bd90612347565b80601f01602080910402602001604051908101604052809291908181526020018280546116e990612347565b80156117365780601f1061170b57610100808354040283529160200191611736565b820191906000526020600020905b81548152906001019060200180831161171957829003601f168201915b505050505081526020016003820154815260200160048201805461175990612347565b80601f016020809104026020016040519081016040528092919081815260200182805461178590612347565b80156117d25780601f106117a7576101008083540402835291602001916117d2565b820191906000526020600020905b8154815290600101906020018083116117b557829003601f168201915b50505050508152505090506117e3565b5b919050565b6040518060a0016040528060608152602001606081526020016060815260200160008152602001606081525090565b82805461182390612347565b90600052602060002090601f0160209004810192826118455760008555611893565b82601f106118565780548555611893565b8280016001018555821561189357600052602060002091601f016020900482015b82811115611892578254825591600101919060010190611877565b5b5090506118a0919061196a565b5090565b5080546118b090612347565b6000825580601f106118c257506118e1565b601f0160209004906000526020600020908101906118e0919061196a565b5b50565b8280546118f090612347565b90600052602060002090601f0160209004810192826119125760008555611959565b82601f1061192b57805160ff1916838001178555611959565b82800160010185558215611959579182015b8281111561195857825182559160200191906001019061193d565b5b509050611966919061196a565b5090565b5b8082111561198357600081600090555060010161196b565b5090565b6000604051905090565b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6119e9826119a0565b810181811067ffffffffffffffff82111715611a0857611a076119b1565b5b80604052505050565b6000611a1b611987565b9050611a2782826119e0565b919050565b600080fd5b600080fd5b600080fd5b600067ffffffffffffffff821115611a5657611a556119b1565b5b611a5f826119a0565b9050602081019050919050565b82818337600083830152505050565b6000611a8e611a8984611a3b565b611a11565b905082815260208101848484011115611aaa57611aa9611a36565b5b611ab5848285611a6c565b509392505050565b600082601f830112611ad257611ad1611a31565b5b8135611ae2848260208601611a7b565b91505092915050565b6000819050919050565b611afe81611aeb565b8114611b0957600080fd5b50565b600081359050611b1b81611af5565b92915050565b600060a08284031215611b3757611b3661199b565b5b611b4160a0611a11565b9050600082013567ffffffffffffffff811115611b6157611b60611a2c565b5b611b6d84828501611abd565b600083015250602082013567ffffffffffffffff811115611b9157611b90611a2c565b5b611b9d84828501611abd565b602083015250604082013567ffffffffffffffff811115611bc157611bc0611a2c565b5b611bcd84828501611abd565b6040830152506060611be184828501611b0c565b606083015250608082013567ffffffffffffffff811115611c0557611c04611a2c565b5b611c1184828501611abd565b60808301525092915050565b600060208284031215611c3357611c32611991565b5b600082013567ffffffffffffffff811115611c5157611c50611996565b5b611c5d84828501611b21565b91505092915050565b60008060408385031215611c7d57611c7c611991565b5b600083013567ffffffffffffffff811115611c9b57611c9a611996565b5b611ca785828601611abd565b925050602083013567ffffffffffffffff811115611cc857611cc7611996565b5b611cd485828601611abd565b9150509250929050565b60008115159050919050565b611cf381611cde565b82525050565b6000602082019050611d0e6000830184611cea565b92915050565b60008060408385031215611d2b57611d2a611991565b5b6000611d3985828601611b0c565b9250506020611d4a85828601611b0c565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611dba578082015181840152602081019050611d9f565b83811115611dc9576000848401525b50505050565b6000611dda82611d80565b611de48185611d8b565b9350611df4818560208601611d9c565b611dfd816119a0565b840191505092915050565b611e1181611aeb565b82525050565b600060a0830160008301518482036000860152611e348282611dcf565b91505060208301518482036020860152611e4e8282611dcf565b91505060408301518482036040860152611e688282611dcf565b9150506060830151611e7d6060860182611e08565b5060808301518482036080860152611e958282611dcf565b9150508091505092915050565b6000611eae8383611e17565b905092915050565b6000602082019050919050565b6000611ece82611d54565b611ed88185611d5f565b935083602082028501611eea85611d70565b8060005b85811015611f265784840389528151611f078582611ea2565b9450611f1283611eb6565b925060208a01995050600181019050611eee565b50829750879550505050505092915050565b60006020820190508181036000830152611f528184611ec3565b905092915050565b600067ffffffffffffffff821115611f7557611f746119b1565b5b602082029050602081019050919050565b600080fd5b6000611f9e611f9984611f5a565b611a11565b90508083825260208201905060208402830185811115611fc157611fc0611f86565b5b835b8181101561200857803567ffffffffffffffff811115611fe657611fe5611a31565b5b808601611ff38982611abd565b85526020850194505050602081019050611fc3565b5050509392505050565b600082601f83011261202757612026611a31565b5b8135612037848260208601611f8b565b91505092915050565b60006020828403121561205657612055611991565b5b600082013567ffffffffffffffff81111561207457612073611996565b5b61208084828501612012565b91505092915050565b60006020828403121561209f5761209e611991565b5b600082013567ffffffffffffffff8111156120bd576120bc611996565b5b6120c984828501611abd565b91505092915050565b600082825260208201905092915050565b60006120ee82611d80565b6120f881856120d2565b9350612108818560208601611d9c565b612111816119a0565b840191505092915050565b61212581611aeb565b82525050565b600060a082019050818103600083015261214581886120e3565b9050818103602083015261215981876120e3565b9050818103604083015261216d81866120e3565b905061217c606083018561211c565b818103608083015261218e81846120e3565b90509695505050505050565b6000602082840312156121b0576121af611991565b5b60006121be84828501611b0c565b91505092915050565b600060208201905081810360008301526121e181846120e3565b905092915050565b600060a08301600083015184820360008601526122068282611dcf565b915050602083015184820360208601526122208282611dcf565b9150506040830151848203604086015261223a8282611dcf565b915050606083015161224f6060860182611e08565b50608083015184820360808601526122678282611dcf565b9150508091505092915050565b6000602082019050818103600083015261228e81846121e9565b905092915050565b600081905092915050565b60006122ac82611d80565b6122b68185612296565b93506122c6818560208601611d9c565b80840191505092915050565b60006122de82846122a1565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061235f57607f821691505b6020821081141561237357612372612318565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006123b382611aeb565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156123e6576123e5612379565b5b600182019050919050565b60008190508160005260206000209050919050565b6000815461241381612347565b61241d8186612296565b9450600182166000811461243857600181146124495761247c565b60ff1983168652818601935061247c565b612452856123f1565b60005b8381101561247457815481890152600182019150602081019050612455565b838801955050505b50505092915050565b60006124918284612406565b915081905092915050565b60006124a782611aeb565b91506124b283611aeb565b9250828210156124c5576124c4612379565b5b828203905092915050565b60006124db82611aeb565b91506124e683611aeb565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561251b5761251a612379565b5b828201905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea26469706673582212209028305121841150273221ae9ebb09d64cf1cc28828c6a93b26bf1b0d71d531c64736f6c634300080a0033";

    public static final String FUNC_BATCHDELETE = "batchDelete";

    public static final String FUNC_DELETEHONOR = "deleteHonor";

    public static final String FUNC_GETALL = "getAll";

    public static final String FUNC_GETENTITY = "getEntity";

    public static final String FUNC_GETLISTPAGE = "getListPage";

    public static final String FUNC_HONORINSERTED = "honorInserted";

    public static final String FUNC_HONORKEY = "honorKey";

    public static final String FUNC_HONORS = "honors";

    public static final String FUNC_ISEQUAL = "isEqual";

    public static final String FUNC_REMOVEHONORKEYATINDEX = "removeHonorKeyAtIndex";

    public static final String FUNC_SAVE = "save";

    public static final Event DELETEMSG_EVENT = new Event("DeleteMsg",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected HonorContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected HonorContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected HonorContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected HonorContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> deleteHonor(Honor _honor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEHONOR,
                Arrays.<Type>asList(_honor),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Honor>>() {}));
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

    public RemoteFunctionCall<Honor> getEntity(Honor _honor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETENTITY,
                Arrays.<Type>asList(_honor),
                Arrays.<TypeReference<?>>asList(new TypeReference<Honor>() {}));
        return executeRemoteCallSingleValueReturn(function, Honor.class);
    }

    public RemoteFunctionCall<List> getListPage(BigInteger begin, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTPAGE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(begin),
                        new org.web3j.abi.datatypes.generated.Uint256(end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Honor>>() {}));
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

    public RemoteFunctionCall<Boolean> honorInserted(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HONORINSERTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> honorKey(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HONORKEY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple5<String, String, String, BigInteger, String>> honors(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HONORS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, String, BigInteger, String>>(function,
                new Callable<Tuple5<String, String, String, BigInteger, String>>() {
                    @Override
                    public Tuple5<String, String, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, BigInteger, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (String) results.get(4).getValue());
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

    public RemoteFunctionCall<TransactionReceipt> removeHonorKeyAtIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEHONORKEYATINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> save(Honor _honor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAVE,
                Arrays.<Type>asList(_honor),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static HonorContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HonorContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HonorContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HonorContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static HonorContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new HonorContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static HonorContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HonorContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HonorContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(HonorContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<HonorContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(HonorContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<HonorContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(HonorContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<HonorContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(HonorContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Honor extends DynamicStruct {
        public String id;

        public String honorName;

        public String honorLevel;

        public BigInteger acquireTime;

        public String proofPic;

        public Honor(String id, String honorName, String honorLevel, BigInteger acquireTime, String proofPic) {
            super(new org.web3j.abi.datatypes.Utf8String(id),new org.web3j.abi.datatypes.Utf8String(honorName),new org.web3j.abi.datatypes.Utf8String(honorLevel),new org.web3j.abi.datatypes.generated.Uint256(acquireTime),new org.web3j.abi.datatypes.Utf8String(proofPic));
            this.id = id;
            this.honorName = honorName;
            this.honorLevel = honorLevel;
            this.acquireTime = acquireTime;
            this.proofPic = proofPic;
        }

        public Honor(Utf8String id, Utf8String honorName, Utf8String honorLevel, Uint256 acquireTime, Utf8String proofPic) {
            super(id,honorName,honorLevel,acquireTime,proofPic);
            this.id = id.getValue();
            this.honorName = honorName.getValue();
            this.honorLevel = honorLevel.getValue();
            this.acquireTime = acquireTime.getValue();
            this.proofPic = proofPic.getValue();
        }
    }

    public static class DeleteMsgEventResponse extends BaseEventResponse {
        public Boolean code;
    }
}