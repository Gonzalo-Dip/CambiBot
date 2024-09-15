# CambiBot
 **CambiBot** es un bot de Discord diseñado para proporcionar cotizaciones de divisas y noticias argentinas en tiempo real. Ideal para quienes necesitan información financiera actualizada y relevante sobre Argentina, CambiBot facilita el acceso a datos de mercado y noticias importantes directamente en tu servidor de Discord. <br> 
 
 ## How to use
 Replace `self.prefix = "cp2"` with any prefix of your choice. <br>
 Replace `self.token = "TOKEN"` with user account token. <br>
 To copy a server use command |**\<prefix>\<command> \<GuildID-Original> \<GuildID-Target>**| <br>
 *GuildID-Original* - ID of the server you wish to copy. <br>
 *GuildID-Target* - ID of the server you wish to overwrite. (I recomend using a new one) <br>
 
 ## Features
 - Copy all roles with setting
 - Copy all emotes
 - Copy almost all channels with permissions
 - Copy hidden channels
 - Copy server name and icon
 
 ## Missing features from first version
 - Copy specified amount of messages (embeds and files included)
 - Send invite link to all members of the original server
 - Add public bots from the original server (not possible anymore)
 
 ## Known issues
Some text and voice channels can't be copied. <br>
copypaste.py | line 143 and 104 <br>
<br>
 It's not possible to start the selfbot without `fetch_offline_members=False`.<br>
 main.py | line 13 <br>

## Note
This code is based on a old CopyPaste selfbot, so it's pretty bad. <br> 
If you have any ideas how to improve it feel free to do so.




<img align="right" width="375" alt="GIF" src="https://github.com/vimalverma558/vimalverma558/blob/v2/img/dino.gif" />
  <img src="https://discord.rovelstars.com/assets/img/bot/logo.svg" height='100px' width='100px' />

