/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixengine.messages.fix44.mbtrading

import fixengine.messages.{
  BusinessMessageReject => BusinessMessageRejectTrait,
  CollateralInquiry => CollateralInquiryTrait,
  ExecutionReport => ExecutionReportTrait,
  Logon => LogonTrait,
  NewOrderMultiLeg => NewOrderMultiLegTrait,
  NewOrderSingle => NewOrderSingleTrait,
  NewsMessage => NewsMessageTrait,
  OrderCancelReplaceRequest => OrderCancelReplaceRequestTrait,
  OrderCancelRequest => OrderCancelRequestTrait,
  RequestForPositions => RequestForPositionsTrait,
  TradingSessionStatus => TradingSessionStatusTrait
}
import fixengine.messages.{
  AbstractMessage,
  MessageHeader,
  MessageVisitor,
  RepeatingGroup,
  RepeatingGroupInstance,
  Required
}
import fixengine.tags.fix42.{
  Account,
  AvgPx,
  BusinessRejectRefID,
  ClOrdID,
  ClientID,
  Commission,
  ComplianceID,
  CumQty,
  DiscretionInst,
  DiscretionOffset,
  EffectiveTime,
  EncryptMethod,
  ExDestination,
  ExecID,
  ExecInst,
  ExpireTime,
  HandlInst,
  Headline,
  HeartBtInt,
  LastPx,
  LastShares,
  LeavesQty,
  LinesOfText,
  LocateReqd,
  MaturityMonthYear,
  MaxFloor,
  OrdRejReason,
  OrdStatus,
  OrdType,
  OrderID,
  OrderQty,
  OrigClOrdID,
  OrigTime,
  PegDifference,
  Price,
  PutOrCall,
  RefMsgType,
  RefSeqNo,
  ResetSeqNumFlag,
  SecondaryOrderID,
  SecurityType,
  SendingTime,
  Side,
  StopPx,
  StrikePrice,
  SubscriptionRequestType,
  Symbol,
  Text,
  TimeInForce,
  TradeDate,
  TradeSesReqID,
  TradingSessionID,
  TransactTime,
  UnderlyingSymbol,
  UnsolicitedIndicator,
  Urgency
}
import fixengine.tags.fix43.{
  BusinessRejectReason,
  LegCFICode,
  LegMaturityMonthYear,
  LegPositionEffect,
  LegPrice,
  LegProduct,
  LegRatioQty,
  LegRefID,
  LegSide,
  LegStrikePrice,
  LegSymbol,
  MassStatusReqID,
  MultiLegReportingType,
  NoLegs,
  OrderRestrictions,
  PositionEffect,
  Price2,
  Product,
  SecondaryClOrdID,
  TradSesStatus,
  TradingSessionSubID
}
import fixengine.tags.fix44.{
  ClearingBusinessDate,
  CollInquiryID,
  ExecType,
  LastRptRequested,
  LongQty,
  MessageEncoding,
  NoTrdRegTimestamps,
  Password,
  PosMaintRptID,
  PosReqID,
  PosReqResult,
  SettlPrice,
  ShortQty,
  TotalNumPosReports,
  TrdRegTimestamp,
  TrdRegTimestampOrigin,
  TrdRegTimestampType,
  Username
}
import fixengine.tags.fix44.mbtrading.{
  LiquidityTag,
  MBTInternalOrderId,
  MBTXAggressive,
  OrderGroupID1,
  PosBuyPowerUsed,
  PosEquityUsed,
  PosPendBuy,
  PosPendSell,
  PosRealizedPNL
}

class CollateralInquiry(header: MessageHeader) extends AbstractMessage(header) with CollateralInquiryTrait {
  field(Account.Tag)
  field(SubscriptionRequestType.Tag)
  field(Username.Tag)
  field(Password.Tag)
  field(CollInquiryID.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class NewOrderMultiLeg(header: MessageHeader) extends AbstractMessage(header) with NewOrderMultiLegTrait {
  field(Account.Tag)
  field(ClOrdID.Tag)
  field(ExecInst.Tag, Required.NO)
  field(HandlInst.Tag)
  field(OrderQty.Tag)
  field(OrdType.Tag)
  field(StopPx.Tag, Required.NO)
  field(Side.Tag)
  field(TimeInForce.Tag)
  field(TransactTime.Tag)
  field(ExDestination.Tag)
  field(NoTrdRegTimestamps.Tag, Required.NO)
  field(TrdRegTimestamp.Tag, Required.NO)
  field(TrdRegTimestampType.Tag, Required.NO)
  field(TrdRegTimestampOrigin.Tag, Required.NO)
  group(new RepeatingGroup(NoLegs.Tag) {
    override def newInstance = new RepeatingGroupInstance(LegSymbol.Tag) {
      field(LegCFICode.Tag)
      field(LegMaturityMonthYear.Tag)
      field(LegStrikePrice.Tag)
      field(LegPositionEffect.Tag)
      field(LegSide.Tag)
      field(LegRatioQty.Tag)
      field(LegRefID.Tag)
      field(Username.Tag)
      field(Password.Tag)
    }
  }, Required.NO)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class NewOrderSingle(header: MessageHeader) extends AbstractMessage(header) with NewOrderSingleTrait {
  field(Account.Tag)
  field(ClOrdID.Tag)
  field(ExecInst.Tag, Required.NO)
  field(HandlInst.Tag)
  field(OrderQty.Tag)
  field(OrdType.Tag)
  field(StopPx.Tag, Required.NO)
  field(SendingTime.Tag)
  field(Symbol.Tag)
  field(Price.Tag, Required.NO)
  field(Side.Tag)
  field(TimeInForce.Tag)
  field(TransactTime.Tag)
  field(StopPx.Tag, Required.NO)
  field(ExDestination.Tag)
  field(MaxFloor.Tag, Required.NO)
  field(LocateReqd.Tag, Required.NO)
  field(ExpireTime.Tag, new Required() {
    override def isRequired() = {
      getEnum(TimeInForce.Tag).equals(TimeInForce.GoodTillDate)
    }
  })
  field(SecurityType.Tag, Required.NO)
  field(EffectiveTime.Tag, Required.NO)
  field(MaturityMonthYear.Tag, Required.NO)
  field(PutOrCall.Tag, Required.NO)
  field(StrikePrice.Tag, Required.NO)
  field(PegDifference.Tag, Required.NO)
  field(DiscretionInst.Tag, Required.NO)
  field(DiscretionOffset.Tag, Required.NO)
  field(ComplianceID.Tag, Required.NO)
  field(SecondaryClOrdID.Tag, Required.NO)
  field(OrderRestrictions.Tag, Required.NO)
  field(Username.Tag)
  field(LegPrice.Tag, Required.NO)
  field(Price2.Tag, Required.NO)
  field(NoTrdRegTimestamps.Tag, Required.NO)
  field(TrdRegTimestamp.Tag, Required.NO)
  field(TrdRegTimestampType.Tag, Required.NO)
  field(TrdRegTimestampOrigin.Tag, Required.NO)
  field(MBTXAggressive.Tag, Required.NO)
  field(OrderGroupID1.Tag, Required.NO)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class OrderCancelRequest(header: MessageHeader) extends AbstractMessage(header) with OrderCancelRequestTrait {
  field(Account.Tag)
  field(ClOrdID.Tag)
  field(OrigClOrdID.Tag)
  field(Side.Tag)
  field(Symbol.Tag)
  field(TransactTime.Tag)
  field(Username.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class OrderCancelReplaceRequest(header: MessageHeader) extends AbstractMessage(header) with OrderCancelReplaceRequestTrait {
  field(Account.Tag)
  field(ClOrdID.Tag)
  field(HandlInst.Tag)
  field(OrderQty.Tag)
  field(OrdType.Tag)
  field(OrigClOrdID.Tag)
  field(Price.Tag)
  field(Side.Tag)
  field(Symbol.Tag)
  field(TimeInForce.Tag)
  field(TransactTime.Tag)
  field(Username.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class ExecutionReport(header: MessageHeader) extends AbstractMessage(header) with ExecutionReportTrait {
  field(Account.Tag)
  field(AvgPx.Tag)
  field(ClOrdID.Tag)
  field(Commission.Tag, Required.NO)
  field(CumQty.Tag)
  field(ExecID.Tag)
  field(ExecInst.Tag, Required.NO)
  field(LastPx.Tag, Required.NO)
  field(LastShares.Tag, Required.NO)
  field(OrderID.Tag)
  field(OrderQty.Tag, Required.NO)
  field(OrdStatus.Tag)
  field(OrdType.Tag, Required.NO)
  field(OrigClOrdID.Tag, Required.NO)
  field(Price.Tag, Required.NO)
  field(Side.Tag)
  field(Symbol.Tag)
  field(Text.Tag, Required.NO)
  field(TimeInForce.Tag, Required.NO)
  field(TransactTime.Tag, Required.NO)
  field(PositionEffect.Tag, Required.NO)
  field(StopPx.Tag, Required.NO)
  field(ExDestination.Tag, Required.NO)
  field(OrdRejReason.Tag, Required.NO)
  field(ClientID.Tag)
  field(MaxFloor.Tag, Required.NO)
  field(ExpireTime.Tag, Required.NO)
  field(ExecType.Tag)
  field(LeavesQty.Tag)
  field(SecurityType.Tag, Required.NO)
  field(EffectiveTime.Tag, Required.NO)
  field(SecondaryOrderID.Tag, Required.NO)
  field(UnderlyingSymbol.Tag, Required.NO)
  field(ComplianceID.Tag, Required.NO)
  field(DiscretionInst.Tag, Required.NO)
  field(DiscretionOffset.Tag, Required.NO)
  field(MultiLegReportingType.Tag, Required.NO)
  field(Product.Tag, Required.NO)
  field(OrderRestrictions.Tag, Required.NO)
  field(MassStatusReqID.Tag, Required.NO)
  group(new RepeatingGroup(NoLegs.Tag) {
    override def newInstance:RepeatingGroupInstance = {
      return new RepeatingGroupInstance(LegPrice.Tag) {
        field(LegSymbol.Tag, Required.NO)
        field(LegProduct.Tag, Required.NO)
        field(LegStrikePrice.Tag, Required.NO)
        field(LegRatioQty.Tag, Required.NO)
        field(LegSide.Tag, Required.NO)
        field(LegRefID.Tag, Required.NO)
      }
    }
  }, Required.NO)
  field(LastRptRequested.Tag, Required.NO)
  field(LiquidityTag.Tag, Required.NO)
  field(PosRealizedPNL.Tag, Required.NO)
  field(OrderGroupID1.Tag, Required.NO)
  field(Price2.Tag, Required.NO)
  field(MBTInternalOrderId.Tag, Required.NO)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}
class TradingSessionStatus(header: MessageHeader) extends AbstractMessage(header) with TradingSessionStatusTrait {
  field(TradeSesReqID.Tag, Required.NO)
  field(TradingSessionID.Tag, Required.NO)
  field(TradSesStatus.Tag, Required.NO)
  field(TradingSessionSubID.Tag, Required.NO)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class RequestForPositions(header: MessageHeader) extends AbstractMessage(header) with RequestForPositionsTrait {
  field(Account.Tag)
  field(Commission.Tag)
  field(Side.Tag)
  field(Symbol.Tag)
  field(TradeDate.Tag)
  field(SubscriptionRequestType.Tag)
  field(UnsolicitedIndicator.Tag)
  field(Price2.Tag)
  field(LongQty.Tag)
  field(ShortQty.Tag)
  field(PosReqID.Tag)
  field(ClearingBusinessDate.Tag)
  field(PosMaintRptID.Tag)
  field(TotalNumPosReports.Tag)
  field(SettlPrice.Tag)
  field(PosPendBuy.Tag)
  field(PosPendSell.Tag)
  field(PosBuyPowerUsed.Tag)
  field(PosRealizedPNL.Tag)
  field(PosEquityUsed.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class News(header: MessageHeader) extends AbstractMessage(header) with NewsMessageTrait {
  field(LinesOfText.Tag)
  field(OrigTime.Tag)
  field(Text.Tag)
  field(Urgency.Tag)
  field(Headline.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class Logon(header: MessageHeader) extends AbstractMessage(header) with fixengine.messages.Logon {
  field(EncryptMethod.Tag)
  field(HeartBtInt.Tag)
  field(ResetSeqNumFlag.Tag, Required.NO)
  field(MessageEncoding.Tag, Required.NO)
  field(Password.Tag, Required.NO)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}

class BusinessMessageReject(header: MessageHeader) extends AbstractMessage(header) with BusinessMessageRejectTrait {
  field(RefSeqNo.Tag)
  field(Text.Tag, Required.NO)
  field(RefMsgType.Tag)
  field(BusinessRejectRefID.Tag, Required.NO)
  field(BusinessRejectReason.Tag)
  override def apply(visitor: MessageVisitor) = visitor.visit(this)
}
