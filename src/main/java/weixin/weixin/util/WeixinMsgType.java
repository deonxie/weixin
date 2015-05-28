package weixin.weixin.util;

public class WeixinMsgType {
	public static enum MsgType{
		TEXT("text"),LOCATION("location"),LINK("link"),
		IMAGE("image"), VOICE("voice"), VIDEO("video"),SHORTVIDEO("shortvideo"),
		EVENT("event");
		private String type;
		MsgType(String type){
			this.type = type;
		}
		public String getType(){
			return this.type;
		}
	}
	
	public static enum EventType{
		VIEW("view"),CLICK("click"),LOCATION("location"),
		SUBSCRIBE("subscribe"),SCAN("scan"),UNSUBSCRIBE("unsubscribe");
		private String type;
		EventType(String type){
			this.type = type;
		}
		public String getType(){
			return this.type;
		}
	}
	
	public static enum MsgField{
		FROM_USER("FromUserName"),TO_USER("ToUserName"),
		CREATE_TIME("CreateTime"),MSG_TYPE("MsgType"),
		CONTENT("Content"),MSG_ID("MsgId"),
		PICURL("PicUrl"),MEDIA_ID("MediaId"),
		TITLE("Title"),
		FORMAT("Format"),THUMB_MEDIAID("ThumbMediaId"),
		LOCATION_X("Location_X"),LOCATION_Y("Location_Y"),
		SCALE("Scale"),LABEL("Label"),
		DESCRIPRION("Description"),URL("Url"),
		EVENT("Event"),EVENT_KEY("EventKey"),
		LATITUDE("Latitude"), LONGITUDE("Longitude"),
		TICKET("Ticket"),PRECISION("Precision")
		;
		
		private String field;
		MsgField(String field){
			this.field = field;
		}
		public String getField(){
			return this.field;
		}
	}

}
