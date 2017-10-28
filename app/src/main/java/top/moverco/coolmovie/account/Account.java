package top.moverco.coolmovie.account;

/**
 * Created by Moverco.
 */

public class Account {

    /**
     * avatar : {"gravatar":{"hash":"c9e9fc152ee756a900db85757c29815d"}}
     * id : 548
     * iso_639_1 : en
     * iso_3166_1 : CA
     * name : Travis Bell
     * include_adult : true
     * username : travisbell
     */

    private AvatarBean avatar;
    private int id;
    private String iso_639_1;
    private String iso_3166_1;
    private String name;
    private boolean include_adult;
    private String username;

    public AvatarBean getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInclude_adult() {
        return include_adult;
    }

    public void setInclude_adult(boolean include_adult) {
        this.include_adult = include_adult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class AvatarBean {
        /**
         * gravatar : {"hash":"c9e9fc152ee756a900db85757c29815d"}
         */

        private GravatarBean gravatar;

        public GravatarBean getGravatar() {
            return gravatar;
        }

        public void setGravatar(GravatarBean gravatar) {
            this.gravatar = gravatar;
        }

        public static class GravatarBean {
            /**
             * hash : c9e9fc152ee756a900db85757c29815d
             */

            private String hash;

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }
        }
    }
}
