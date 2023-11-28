export default function getSession() {
    return localStorage.getItem("token_user");
}