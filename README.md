
SUBMIT IDEA

Api for collecting ideas for Api. User login with email authentication.
Access to endpoints depending on the role (User / Mod / Admin).

Application users are divided into:
1) User unconfirmed 
   
    A user who has provided his data but has not yet confirmed it by e-mail
            
        -register ("/sing-up")
2) User confirmed

    User who can:

        -login ("/login")
        -add an entry ("/addIdeas")
        -view accepted entries ("/getAcceptedIdeas")
3) Mod
user 
        
        confirmed +
        -show unaccepted entries ("/getUnacceptedIdeas")
        -delete entries ("/deleteIdeaById")
        -accepted entries ("/acceptedIdeas")
   
4) Admin

        mod +
        -delate users
        -change User to Mod ("/changeRoleToMod")


created by qubiak