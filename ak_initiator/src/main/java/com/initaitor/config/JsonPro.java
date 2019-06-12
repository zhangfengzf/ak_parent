package com.example.initiator.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @ClassName: JsonPro
 * @Description: 获取配置文件中的属性值
 * @Author 张锋
 * @Date 2018/9/19 18:32
 * @Version 1.0
 */
@Component
@PropertySource("classpath:config/yyq.properties")
public class JsonPro {

    @Value("${yyq.chinese}")
    private String chinese;
    @Value("${yyq.chineseKEY}")
    private String chineseKEY;
    @Value("${yyq.english}")
    private String english;
    @Value("${yyq.englishKEY}")
    private String englishKEY;
    @Value("${yyq.other}")
    private String other;
    @Value("${yyq.adminUrl}")
    private String adminUrl;
    @Value("${yyq.asrUrl}")
    private String asrUrl;
    @Value("${yyq.asrUrlKEY}")
    private String asrUrlKEY;
    @Value("${yyq.audioUrlCN}")
    private String audioUrlCN;
    @Value("${yyq.audioUrlEN}")
    private String audioUrlEN;
    @Value("${yyq.audioUrlKEY}")
    private String audioUrlKEY;
    @Value("${yyq.webUrl}")
    private String webUrl;
    @Value("${yyq.webUrlKEY}")
    private String webUrlKEY;
    @Value("${yyq.serverUrl}")
    private String serverUrl;
    @Value("${yyq.serverUrlKEY}")
    private String serverUrlKEY;
    @Value("${yyq.adminAddress}")
    private String adminAddress;
    @Value("${yyq.jsonName}")
    private String jsonName;
    @Value("${yyq.addressOpen}")
    private String addressOpen;
    @Value("${yyq.addressClose}")
    private String addressClose;
    @Value("${yyq.addressJson}")
    private String addressJson;
    @Value("${yyq.startNginxAddress}")
    private String startNginxAddress;
    @Value("${yyq.stopNginxAddress}")
    private String stopNginxAddress;
    @Value("${yyq.startFile}")
    private String startFile;
    @Value("${yyq.stopFile}")
    private String stopFile;
    @Value("${yyq.jsonNameType}")
    private String jsonNameType;
    @Value("${yyq.jsonNameTypeChineseValue}")
    private String jsonNameTypeChineseValue;
    @Value("${yyq.jsonNameTypeEnglishValue}")
    private String jsonNameTypeEnglishValue;
    @Value("${yyq.jsonNameTypeOtherValue}")
    private String jsonNameTypeOtherValue;
    @Value("${yyq.recordAddress}")
    private String recordAddress;
    @Value("${yyq.fileAddressOpen}")
    private String fileAddressOpen;
    @Value("${yyq.jarAddress}")
    private String jarAddress;
    @Value("${yyq.jarAddressBatFileName}")
    private String jarAddressBatFileName;
    @Value("${yyq.name}")
    private String name;
    @Value("${yyq.hostname}")
    private String hostname;
    @Value("${yyq.port}")
    private Integer port;
    @Value("${yyq.userName}")
    private String userName;
    @Value("${yyq.userPassword}")
    private String userPassword;
    @Value("${yyq.jarAddressBatFileNameStop}")
    private String jarAddressBatFileNameStop;
    @Value("${yyq.interpreter1}")
    private String interpreter1;
    @Value("${yyq.interpreter2}")
    private String interpreter2;
    @Value("${yyq.filePath}")
    private String filePath;
    @Value("${yyq.fileUrl}")
    private String fileUrl;
    @Value("${yyq.fileUrlKEY}")
    private String fileUrlKEY;
    @Value("${yyq.admin}")
    private String admin;
    @Value("${yyq.adminKEY}")
    private String adminKEY;
    @Value("${yyq.speechNumber}")
    private Integer speechNumber;
    @Value("${yyq.meetingId}")
    private String meetingId;
    @Value("${yyq.Internet}")
    private String Internet;
    @Value("${yyq.ffmpeg}")
    private String ffmpeg;
    @Value("${yyq.audio}")
    private String audio;
    @Value("${yyq.status}")
    private String status;
    @Value("${yyq.InternetURL}")
    private String InternetURL;
    @Value("${yyq.InternetLAN}")
    private String InternetLAN;
    @Value("${yyq.UDPPortCN}")
    private String UDPPortCN;
    @Value("${yyq.UDPPortEN}")
    private String UDPPortEN;
    @Value("${yyq.TCPPort}")
    private String TCPPort;
    @Value("${yyq.addressOpenLANEN}")
    private String addressOpenLANEN;
    @Value("${yyq.addressCloseLANEN}")
    private String addressCloseLANEN;
    @Value("${yyq.addressOpenLANCN}")
    private String addressOpenLANCN;
    @Value("${yyq.addressCloseLANCN}")
    private String addressCloseLANCN;
    @Value("${yyq.TCPOpenURL}")
    private String TCPOpenURL;
    @Value("${yyq.TCPCloseURL}")
    private String TCPCloseURL;
    @Value("${yyq.addressOpenWEBCN}")
    private String addressOpenWEBCN;
    @Value("${yyq.addressOpenWEBEN}")
    private String addressOpenWEBEN;
    @Value("${yyq.addressCloseWEBCN}")
    private String addressCloseWEBCN;
    @Value("${yyq.addressCloseWEBEN}")
    private String addressCloseWEBEN;
    @Value("${yyq.audioUrlCNKEY}")
    private String audioUrlCNKEY;
    @Value("${yyq.audioUrlENKEY}")
    private String audioUrlENKEY;
    public JsonPro() {
    }

    public String getChinese() {
        return chinese;
    }

    public String getAudioUrlCNKEY() {
        return audioUrlCNKEY;
    }

    public void setAudioUrlCNKEY(String audioUrlCNKEY) {
        this.audioUrlCNKEY = audioUrlCNKEY;
    }

    public String getAudioUrlENKEY() {
        return audioUrlENKEY;
    }

    public void setAudioUrlENKEY(String audioUrlENKEY) {
        this.audioUrlENKEY = audioUrlENKEY;
    }

    @Override
    public String toString() {
        return "JsonPro{" +
                "chinese='" + chinese + '\'' +
                ", chineseKEY='" + chineseKEY + '\'' +
                ", english='" + english + '\'' +
                ", englishKEY='" + englishKEY + '\'' +
                ", other='" + other + '\'' +
                ", adminUrl='" + adminUrl + '\'' +
                ", asrUrl='" + asrUrl + '\'' +
                ", asrUrlKEY='" + asrUrlKEY + '\'' +
                ", audioUrlCN='" + audioUrlCN + '\'' +
                ", audioUrlEN='" + audioUrlEN + '\'' +
                ", audioUrlKEY='" + audioUrlKEY + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", webUrlKEY='" + webUrlKEY + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", serverUrlKEY='" + serverUrlKEY + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", jsonName='" + jsonName + '\'' +
                ", addressOpen='" + addressOpen + '\'' +
                ", addressClose='" + addressClose + '\'' +
                ", addressJson='" + addressJson + '\'' +
                ", startNginxAddress='" + startNginxAddress + '\'' +
                ", stopNginxAddress='" + stopNginxAddress + '\'' +
                ", startFile='" + startFile + '\'' +
                ", stopFile='" + stopFile + '\'' +
                ", jsonNameType='" + jsonNameType + '\'' +
                ", jsonNameTypeChineseValue='" + jsonNameTypeChineseValue + '\'' +
                ", jsonNameTypeEnglishValue='" + jsonNameTypeEnglishValue + '\'' +
                ", jsonNameTypeOtherValue='" + jsonNameTypeOtherValue + '\'' +
                ", recordAddress='" + recordAddress + '\'' +
                ", fileAddressOpen='" + fileAddressOpen + '\'' +
                ", jarAddress='" + jarAddress + '\'' +
                ", jarAddressBatFileName='" + jarAddressBatFileName + '\'' +
                ", name='" + name + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", jarAddressBatFileNameStop='" + jarAddressBatFileNameStop + '\'' +
                ", interpreter1='" + interpreter1 + '\'' +
                ", interpreter2='" + interpreter2 + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileUrlKEY='" + fileUrlKEY + '\'' +
                ", admin='" + admin + '\'' +
                ", adminKEY='" + adminKEY + '\'' +
                ", speechNumber=" + speechNumber +
                ", meetingId='" + meetingId + '\'' +
                ", Internet='" + Internet + '\'' +
                ", ffmpeg='" + ffmpeg + '\'' +
                ", audio='" + audio + '\'' +
                ", status='" + status + '\'' +
                ", InternetURL='" + InternetURL + '\'' +
                ", InternetLAN='" + InternetLAN + '\'' +
                ", UDPPortCN='" + UDPPortCN + '\'' +
                ", UDPPortEN='" + UDPPortEN + '\'' +
                ", TCPPort='" + TCPPort + '\'' +
                ", addressOpenLANEN='" + addressOpenLANEN + '\'' +
                ", addressCloseLANEN='" + addressCloseLANEN + '\'' +
                ", addressOpenLANCN='" + addressOpenLANCN + '\'' +
                ", addressCloseLANCN='" + addressCloseLANCN + '\'' +
                ", TCPOpenURL='" + TCPOpenURL + '\'' +
                ", TCPCloseURL='" + TCPCloseURL + '\'' +
                ", addressOpenWEBCN='" + addressOpenWEBCN + '\'' +
                ", addressOpenWEBEN='" + addressOpenWEBEN + '\'' +
                ", addressCloseWEBCN='" + addressCloseWEBCN + '\'' +
                ", addressCloseWEBEN='" + addressCloseWEBEN + '\'' +
                ", audioUrlCNKEY='" + audioUrlCNKEY + '\'' +
                ", audioUrlENKEY='" + audioUrlENKEY + '\'' +
                '}';
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getChineseKEY() {
        return chineseKEY;
    }

    public void setChineseKEY(String chineseKEY) {
        this.chineseKEY = chineseKEY;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getEnglishKEY() {
        return englishKEY;
    }

    public void setEnglishKEY(String englishKEY) {
        this.englishKEY = englishKEY;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    public String getAsrUrl() {
        return asrUrl;
    }

    public void setAsrUrl(String asrUrl) {
        this.asrUrl = asrUrl;
    }

    public String getAsrUrlKEY() {
        return asrUrlKEY;
    }

    public void setAsrUrlKEY(String asrUrlKEY) {
        this.asrUrlKEY = asrUrlKEY;
    }

    public String getAudioUrlCN() {
        return audioUrlCN;
    }

    public void setAudioUrlCN(String audioUrlCN) {
        this.audioUrlCN = audioUrlCN;
    }

    public String getAudioUrlEN() {
        return audioUrlEN;
    }

    public void setAudioUrlEN(String audioUrlEN) {
        this.audioUrlEN = audioUrlEN;
    }

    public String getAudioUrlKEY() {
        return audioUrlKEY;
    }

    public void setAudioUrlKEY(String audioUrlKEY) {
        this.audioUrlKEY = audioUrlKEY;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebUrlKEY() {
        return webUrlKEY;
    }

    public void setWebUrlKEY(String webUrlKEY) {
        this.webUrlKEY = webUrlKEY;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerUrlKEY() {
        return serverUrlKEY;
    }

    public void setServerUrlKEY(String serverUrlKEY) {
        this.serverUrlKEY = serverUrlKEY;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public String getAddressOpen() {
        return addressOpen;
    }

    public void setAddressOpen(String addressOpen) {
        this.addressOpen = addressOpen;
    }

    public String getAddressClose() {
        return addressClose;
    }

    public void setAddressClose(String addressClose) {
        this.addressClose = addressClose;
    }

    public String getAddressJson() {
        return addressJson;
    }

    public void setAddressJson(String addressJson) {
        this.addressJson = addressJson;
    }

    public String getStartNginxAddress() {
        return startNginxAddress;
    }

    public void setStartNginxAddress(String startNginxAddress) {
        this.startNginxAddress = startNginxAddress;
    }

    public String getStopNginxAddress() {
        return stopNginxAddress;
    }

    public void setStopNginxAddress(String stopNginxAddress) {
        this.stopNginxAddress = stopNginxAddress;
    }

    public String getStartFile() {
        return startFile;
    }

    public void setStartFile(String startFile) {
        this.startFile = startFile;
    }

    public String getStopFile() {
        return stopFile;
    }

    public void setStopFile(String stopFile) {
        this.stopFile = stopFile;
    }

    public String getJsonNameType() {
        return jsonNameType;
    }

    public void setJsonNameType(String jsonNameType) {
        this.jsonNameType = jsonNameType;
    }

    public String getJsonNameTypeChineseValue() {
        return jsonNameTypeChineseValue;
    }

    public void setJsonNameTypeChineseValue(String jsonNameTypeChineseValue) {
        this.jsonNameTypeChineseValue = jsonNameTypeChineseValue;
    }

    public String getJsonNameTypeEnglishValue() {
        return jsonNameTypeEnglishValue;
    }

    public void setJsonNameTypeEnglishValue(String jsonNameTypeEnglishValue) {
        this.jsonNameTypeEnglishValue = jsonNameTypeEnglishValue;
    }

    public String getJsonNameTypeOtherValue() {
        return jsonNameTypeOtherValue;
    }

    public void setJsonNameTypeOtherValue(String jsonNameTypeOtherValue) {
        this.jsonNameTypeOtherValue = jsonNameTypeOtherValue;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    public String getFileAddressOpen() {
        return fileAddressOpen;
    }

    public void setFileAddressOpen(String fileAddressOpen) {
        this.fileAddressOpen = fileAddressOpen;
    }

    public String getJarAddress() {
        return jarAddress;
    }

    public void setJarAddress(String jarAddress) {
        this.jarAddress = jarAddress;
    }

    public String getJarAddressBatFileName() {
        return jarAddressBatFileName;
    }

    public void setJarAddressBatFileName(String jarAddressBatFileName) {
        this.jarAddressBatFileName = jarAddressBatFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getJarAddressBatFileNameStop() {
        return jarAddressBatFileNameStop;
    }

    public void setJarAddressBatFileNameStop(String jarAddressBatFileNameStop) {
        this.jarAddressBatFileNameStop = jarAddressBatFileNameStop;
    }

    public String getInterpreter1() {
        return interpreter1;
    }

    public void setInterpreter1(String interpreter1) {
        this.interpreter1 = interpreter1;
    }

    public String getInterpreter2() {
        return interpreter2;
    }

    public void setInterpreter2(String interpreter2) {
        this.interpreter2 = interpreter2;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrlKEY() {
        return fileUrlKEY;
    }

    public void setFileUrlKEY(String fileUrlKEY) {
        this.fileUrlKEY = fileUrlKEY;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAdminKEY() {
        return adminKEY;
    }

    public void setAdminKEY(String adminKEY) {
        this.adminKEY = adminKEY;
    }

    public Integer getSpeechNumber() {
        return speechNumber;
    }

    public void setSpeechNumber(Integer speechNumber) {
        this.speechNumber = speechNumber;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getInternet() {
        return Internet;
    }

    public void setInternet(String internet) {
        Internet = internet;
    }

    public String getFfmpeg() {
        return ffmpeg;
    }

    public void setFfmpeg(String ffmpeg) {
        this.ffmpeg = ffmpeg;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInternetURL() {
        return InternetURL;
    }

    public void setInternetURL(String internetURL) {
        InternetURL = internetURL;
    }

    public String getInternetLAN() {
        return InternetLAN;
    }

    public void setInternetLAN(String internetLAN) {
        InternetLAN = internetLAN;
    }

    public String getUDPPortCN() {
        return UDPPortCN;
    }

    public void setUDPPortCN(String UDPPortCN) {
        this.UDPPortCN = UDPPortCN;
    }

    public String getUDPPortEN() {
        return UDPPortEN;
    }

    public void setUDPPortEN(String UDPPortEN) {
        this.UDPPortEN = UDPPortEN;
    }

    public String getTCPPort() {
        return TCPPort;
    }

    public void setTCPPort(String TCPPort) {
        this.TCPPort = TCPPort;
    }

    public String getAddressOpenLANEN() {
        return addressOpenLANEN;
    }

    public void setAddressOpenLANEN(String addressOpenLANEN) {
        this.addressOpenLANEN = addressOpenLANEN;
    }

    public String getAddressCloseLANEN() {
        return addressCloseLANEN;
    }

    public void setAddressCloseLANEN(String addressCloseLANEN) {
        this.addressCloseLANEN = addressCloseLANEN;
    }

    public String getAddressOpenLANCN() {
        return addressOpenLANCN;
    }

    public void setAddressOpenLANCN(String addressOpenLANCN) {
        this.addressOpenLANCN = addressOpenLANCN;
    }

    public String getAddressCloseLANCN() {
        return addressCloseLANCN;
    }

    public void setAddressCloseLANCN(String addressCloseLANCN) {
        this.addressCloseLANCN = addressCloseLANCN;
    }

    public String getTCPOpenURL() {
        return TCPOpenURL;
    }

    public void setTCPOpenURL(String TCPOpenURL) {
        this.TCPOpenURL = TCPOpenURL;
    }

    public String getTCPCloseURL() {
        return TCPCloseURL;
    }

    public void setTCPCloseURL(String TCPCloseURL) {
        this.TCPCloseURL = TCPCloseURL;
    }

    public String getAddressOpenWEBCN() {
        return addressOpenWEBCN;
    }

    public void setAddressOpenWEBCN(String addressOpenWEBCN) {
        this.addressOpenWEBCN = addressOpenWEBCN;
    }

    public String getAddressOpenWEBEN() {
        return addressOpenWEBEN;
    }

    public void setAddressOpenWEBEN(String addressOpenWEBEN) {
        this.addressOpenWEBEN = addressOpenWEBEN;
    }

    public String getAddressCloseWEBCN() {
        return addressCloseWEBCN;
    }

    public void setAddressCloseWEBCN(String addressCloseWEBCN) {
        this.addressCloseWEBCN = addressCloseWEBCN;
    }

    public String getAddressCloseWEBEN() {
        return addressCloseWEBEN;
    }

    public void setAddressCloseWEBEN(String addressCloseWEBEN) {
        this.addressCloseWEBEN = addressCloseWEBEN;
    }

}
